package com.parking.Ticketing.service;

import com.parking.Ticketing.entity.Booking;
import com.parking.Ticketing.repository.BookingRepository;
import com.parking.Ticketing.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ETicketingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Encrypt the booking data based on booking ID
    public String encryptBookingData(Integer bookingId) throws Exception {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking ID"));

        String bookingData = "Booking ID: " + booking.getBookingId() +
                ", Vehicle No: " + booking.getVehicleNo() +
                ", Start Time: " + booking.getStartTime() +
                ", End Time: " + booking.getEndTime();

        return EncryptionUtil.encrypt(bookingData);
    }

    // Decrypt the encrypted booking data
    public String decryptBookingData(String encryptedData) throws Exception {
        return EncryptionUtil.decrypt(encryptedData);
    }
}
