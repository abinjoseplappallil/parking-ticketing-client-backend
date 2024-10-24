package com.parking.Ticketing.DTO;

public class BookingResponseDTO {
    private Long bookingId;
    private Integer slotId;
    private String message;

    // Constructor
    public BookingResponseDTO(Long bookingId, Integer slotId, String message) {
        this.bookingId = bookingId;
        this.slotId = slotId;
        this.message = message;
    }

    // Getters and Setters
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
