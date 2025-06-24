package org.example.paymentservice.service.serviceImpl;

import org.example.paymentservice.client.ParkingDetailsClient;
import org.example.paymentservice.dto.ParkingDetailsResponse;
import org.example.paymentservice.dto.PaymentDTO;
import org.example.paymentservice.entity.Payment;
import org.example.paymentservice.repo.PaymentRepo;
import org.example.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private ParkingDetailsClient parkingDetailsClient;

    @Override
    public PaymentDTO createPayment(PaymentDTO dto) {
        // Call parking-space-service through Feign client
        ParkingDetailsResponse parkingDetails = parkingDetailsClient.getParkingDetailsById(dto.getParkingDetailsId());

        if (parkingDetails == null) {
            throw new RuntimeException("Parking details not found for id: " + dto.getParkingDetailsId());
        }

        if (parkingDetails.getExitTime() == null) {
            throw new RuntimeException("Vehicle has not exited yet.");
        }

        // Calculate duration and payment amount
        Duration duration = Duration.between(parkingDetails.getEntryTime(), parkingDetails.getExitTime());
        long hours = Math.max(1, duration.toHours());
        double amount = hours * 150.0;

        // Fill DTO with calculated data
        dto.setPaidAmount(amount);
        dto.setPaidDate(LocalDateTime.now());
        dto.setStatus(PaymentDTO.Status.SUCCESS);

        // Save payment entity
        Payment payment = mapToEntity(dto);
        payment = paymentRepo.save(payment);

        return mapToDTO(payment);
    }


    @Override
    public PaymentDTO getPaymentById(Long id) {
        Optional<Payment> payment = paymentRepo.findById(id);
        return payment.map(this::mapToDTO).orElse(null);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepo.deleteById(id);
    }

    // Safely map DTO to Entity
    private Payment mapToEntity(PaymentDTO dto) {
        return new Payment(
                dto.getId(),
                dto.getPaidDate(),
                dto.getPaidAmount(),
                dto.getVehicleId(),
                dto.getParkingDetailsId(),
                Payment.Status.SUCCESS // safely hardcoded
        );
    }

    // Convert Entity to DTO
    private PaymentDTO mapToDTO(Payment payment) {
        return new PaymentDTO(
                payment.getId(),
                payment.getPaidDate(),
                payment.getPaidAmount(),
                payment.getVehicleId(),
                payment.getParkingDetailsId(),
                PaymentDTO.Status.valueOf(payment.getStatus().name())
        );
    }
}
