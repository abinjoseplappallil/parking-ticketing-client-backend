package com.parking.Ticketing.service;


import com.parking.Ticketing.DTO.BookingRequestDTO;
import com.parking.Ticketing.DTO.BookingResponseDTO;
import com.parking.Ticketing.entity.Booking;
import com.parking.Ticketing.entity.ParkingLot;
import com.parking.Ticketing.repository.BookingRepository;
import com.parking.Ticketing.repository.ParkingSlotRepository;
import com.parking.Ticketing.util.DateTimeUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public BookingResponseDTO bookParkingSlot(BookingRequestDTO request) {
        ParkingLot parkingLot = parkingSlotRepository.findById(request.getParkingLotId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid parking lot ID"));

        // Get the total number of slots
        int totalSlots = parkingLot.getSlotCount();
        LocalDateTime startTime = DateTimeUtil.convertStringToLocalDateTime(request.getStartTime());
        LocalDateTime endTime = DateTimeUtil.convertStringToLocalDateTime(request.getEndTime());

        // Fetch the booked slots for the given time range
        List<Integer> bookedSlotIds = bookingRepository.findBookedSlotsForTime(
                request.getParkingLotId(), startTime, endTime);

        // Convert startTime and endTime from String to LocalDateTime

        // Find the first available slot
        for (Integer slotId = 1; slotId <= totalSlots; slotId++) {
            if (!bookedSlotIds.contains(slotId)) {
                // Book the first available slot
                Booking newBooking = new Booking();
                newBooking.setParkingLot(parkingLot);
                newBooking.setSlotId(slotId);
                newBooking.setStartTime(startTime);
                newBooking.setEndTime(endTime);
                newBooking.setVehicleNo(request.getVehicleNo());
                Booking savedBooking = bookingRepository.save(newBooking);

                return new BookingResponseDTO(savedBooking.getBookingId(), slotId, "Parking slot booked successfully.");
            }
        }

        return new BookingResponseDTO(null, null, "No parking slots available for the requested time.");
    }

    public int verifyBookingByVehicleNo(String vehicleNo) {
        LocalDateTime currentTime = LocalDateTime.now();
        Booking booking = bookingRepository.findBookingByVehicleNoAndCurrentTime(vehicleNo, currentTime);

        // Return 1 if booking exists, otherwise 0
        return (booking != null) ? 1 : 0;
    }
}
