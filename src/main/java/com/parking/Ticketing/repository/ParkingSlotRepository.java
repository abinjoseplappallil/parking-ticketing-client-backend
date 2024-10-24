package com.parking.Ticketing.repository;

import com.parking.Ticketing.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingLot, Long> {
    List<ParkingLot> findByAreaId(int areaId);
}
