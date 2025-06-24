package org.example.parkingservice.service;

import org.example.parkingservice.entity.ParkingSpace;
import org.example.parkingservice.entity.ParkingSpaceDetails;

import java.util.List;
import java.util.Optional;

public interface ParkingSpaceServiceDetails {

    ParkingSpaceDetails addParkingDetail(Long spaceId, ParkingSpaceDetails detail);
    List<ParkingSpaceDetails> getDetailsBySpace(Long spaceId);
    void deleteDetail(Long detailId);
    ParkingSpaceDetails getDetailById(Long detailId);

}
