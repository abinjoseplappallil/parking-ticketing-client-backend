package com.parking.Ticketing.DTO;

import com.parking.Ticketing.entity.ParkingLot;

public class ParkingSlotDTO {

    private int slotId;
    private Integer areaId;
    private double latitude;
    private double longitude;
    private int totalSlots;
    private double distance; // Distance from the user's location
    private int availableSlots; // Number of available slots at the specified time

    // Constructor
    public ParkingSlotDTO(ParkingLot parkingSlot, double distance, int availableSlots) {
        this.slotId = parkingSlot.getParkingLotId();
        this.areaId = parkingSlot.getAreaId();
        this.latitude = parkingSlot.getLatitude();
        this.longitude = parkingSlot.getLongitude();
        this.totalSlots = parkingSlot.getSlotCount();
        this.distance = distance;
        this.availableSlots = availableSlots;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }
}
