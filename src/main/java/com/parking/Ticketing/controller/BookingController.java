package com.parking.Ticketing.controller;

import com.parking.Ticketing.DTO.BookingRequestDTO;
import com.parking.Ticketing.DTO.BookingResponseDTO;
import com.parking.Ticketing.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eticketing")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/reserve")
    public ResponseEntity<BookingResponseDTO> bookParkingSlot(@RequestBody BookingRequestDTO request) {
        BookingResponseDTO response = bookingService.bookParkingSlot(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/verifyBooking")
    public int verifyBooking(@RequestParam String vehicleNo) {
        return bookingService.verifyBookingByVehicleNo(vehicleNo);
    }
}
