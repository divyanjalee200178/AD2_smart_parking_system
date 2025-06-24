package org.example.parkingservice.service.impl;

import org.example.parkingservice.entity.ParkingSpace;
import org.example.parkingservice.entity.ParkingSpaceDetails;
import org.example.parkingservice.repo.ParkingSpaceDetailsRepo;
import org.example.parkingservice.repo.ParkingSpaceRepo;
import org.example.parkingservice.service.ParkingSpaceService;
import org.example.parkingservice.service.ParkingSpaceServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpaceServiceDetailsImpl implements ParkingSpaceServiceDetails {

    @Autowired
    private ParkingSpaceRepo parkingSpaceRepo;

    @Autowired
    private ParkingSpaceDetailsRepo parkingSpaceDetailsRepo;


    // --- ParkingSpaceDetails logic ---

    @Override
    public ParkingSpaceDetails addParkingDetail(Long spaceId, ParkingSpaceDetails detail) {
        ParkingSpace space = parkingSpaceRepo.findById(spaceId)
                .orElseThrow(() -> new RuntimeException("Parking space not found"));
        detail.setParkingSpace(space);

        if (detail.getEntryTime() != null && detail.getExitTime() != null) {
            long duration = Duration.between(detail.getEntryTime(), detail.getExitTime()).toMinutes();
            detail.setDuration(duration);
        }

        return parkingSpaceDetailsRepo.save(detail);
    }

    @Override
    public List<ParkingSpaceDetails> getDetailsBySpace(Long spaceId) {
        return parkingSpaceDetailsRepo.findByParkingSpaceId(spaceId);
    }

    @Override
    public void deleteDetail(Long detailId) {
        parkingSpaceDetailsRepo.deleteById(detailId);
    }

    @Override
    public ParkingSpaceDetails getDetailById(Long detailId) {
        return parkingSpaceDetailsRepo.findById(detailId)
                .orElseThrow(() -> new RuntimeException("Parking detail not found with id: " + detailId));
    }

}
