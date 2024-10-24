package com.parking.Ticketing.entity;


import jakarta.persistence.*;

@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int parkingLotId;

    private Integer areaId;

    @Column(name = "slot_count") // Explicitly maps the field to 'slot_count' column
    private Integer slotCount;

    @Column(name = "longitude") // Explicitly maps the field to 'long' column
    private Float longitude;

    @Column(name = "latitude") // Explicitly maps the field to 'lat' column
    private Float latitude;

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getSlotCount() {
        return slotCount;
    }

    public void setSlotCount(Integer slotCount) {
        this.slotCount = slotCount;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }


}