package com.parking.Ticketing.controller;

import com.parking.Ticketing.service.ETicketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eticketing")
public class ETicketingController {

    @Autowired
    private ETicketingService eTicketingService;

    // API to encrypt booking data by booking ID
    @GetMapping("/encryptBooking")
    public String encryptBooking(@RequestParam Integer bookingId) {
        try {
            return eTicketingService.encryptBookingData(bookingId);
        } catch (Exception e) {
            e.printStackTrace();
            return "Encryption failed";
        }
    }

    // API to decrypt booking data
    @GetMapping("/decryptBooking")
    public String decryptBooking(@RequestParam String encryptedData) {
        try {
            return eTicketingService.decryptBookingData(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return "Decryption failed";
        }
    }
}
