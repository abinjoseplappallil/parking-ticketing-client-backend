package com.parking.Ticketing.repository;

import com.parking.Ticketing.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    // Custom query to count the used slots for a specific parking lot and booking time
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.parkingLot.parkingLotId = :slotId AND :bookingTime BETWEEN b.startTime AND b.endTime")
    int countUsedSlotsForTime(int slotId, LocalDateTime bookingTime);

    // Custom query to find all booked slots in the given time range
    @Query("SELECT b.slotId FROM Booking b WHERE b.parkingLot.parkingLotId = :parkingLotId AND " +
            "(b.startTime < :endTime AND b.endTime > :startTime)")
    List<Integer> findBookedSlotsForTime(Long parkingLotId, LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT b FROM Booking b WHERE b.vehicleNo = :vehicleNo AND :currentTime BETWEEN b.startTime AND b.endTime")
    Booking findBookingByVehicleNoAndCurrentTime(@Param("vehicleNo") String vehicleNo, @Param("currentTime") LocalDateTime currentTime);
}
