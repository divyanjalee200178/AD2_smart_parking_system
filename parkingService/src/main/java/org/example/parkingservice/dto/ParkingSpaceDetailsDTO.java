package org.example.parkingservice.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.parkingservice.entity.ParkingSpace;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingSpaceDetailsDTO {
    private Long id;

    private String numberPlate;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private Long duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_space_id")
    private ParkingSpace parkingSpace;
}

