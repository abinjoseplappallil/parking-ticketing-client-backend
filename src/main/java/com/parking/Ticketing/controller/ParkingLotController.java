package com.parking.Ticketing.controller;

import com.parking.Ticketing.DTO.NewParkingLotDTO;
import com.parking.Ticketing.entity.ParkingLot;
import com.parking.Ticketing.service.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking")
public class ParkingLotController {

    @Autowired
    private ParkingSlotService parkingLotService;

    // API to add a new parking lot
    @PostMapping("/add")
    public ParkingLot addParkingLot(@RequestBody NewParkingLotDTO parkingLotDTO) {
        return parkingLotService.addParkingLot(parkingLotDTO);
    }
}
