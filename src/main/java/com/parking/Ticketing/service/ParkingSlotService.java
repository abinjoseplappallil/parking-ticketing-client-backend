package com.parking.Ticketing.service;

import com.parking.Ticketing.DTO.NewParkingLotDTO;
import com.parking.Ticketing.DTO.ParkingSlotDTO;
import com.parking.Ticketing.entity.ParkingLot;
import com.parking.Ticketing.repository.BookingRepository;
import com.parking.Ticketing.repository.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingSlotService {

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // Main service method to find nearest parking slots
    public List<ParkingSlotDTO> findNearestParking(Integer areaId, double longitude, double latitude, LocalDateTime bookingTime) {

        // Fetch all parking slots for the given area
        List<ParkingLot> parkingSlots = parkingSlotRepository.findByAreaId(areaId);

        // Calculate available slots and distances
        List<ParkingSlotDTO> nearestParkingSlots = parkingSlots.stream()
                .map(slot -> {
                    double distance = calculateDistance(latitude, longitude, slot.getLatitude(), slot.getLongitude());
                    int availableSlots = calculateAvailableSlots(slot.getParkingLotId(), slot.getSlotCount(), bookingTime);
                    return new ParkingSlotDTO(slot, distance, availableSlots);
                })
                .filter(slotDTO -> slotDTO.getAvailableSlots() > 0) // Filter out slots with no availability
                .sorted(Comparator.comparingDouble(ParkingSlotDTO::getDistance)) // Sort by distance
                .collect(Collectors.toList());

        return nearestParkingSlots;
    }

    // Calculate available slots by subtracting used slots from total
    private int calculateAvailableSlots(int slotId, int totalSlots, LocalDateTime bookingTime) {
        int usedSlots = bookingRepository.countUsedSlotsForTime(slotId, bookingTime);
        return totalSlots - usedSlots;
    }

    // Calculate distance using the Haversine formula
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the Earth in kilometers
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in kilometers
    }
    public ParkingLot addParkingLot(NewParkingLotDTO parkingLotDTO) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setAreaId(parkingLotDTO.getAreaId());
        parkingLot.setSlotCount(parkingLotDTO.getSlotCount());
        parkingLot.setLongitude(parkingLotDTO.getLongitude());
        parkingLot.setLatitude(parkingLotDTO.getLatitude());

        return parkingSlotRepository.save(parkingLot);
    }
}