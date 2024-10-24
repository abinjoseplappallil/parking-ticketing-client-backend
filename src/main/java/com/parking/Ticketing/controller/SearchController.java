package com.parking.Ticketing.controller;


import com.parking.Ticketing.DTO.ParkingSlotDTO;
import com.parking.Ticketing.service.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/api/parking")
public class SearchController {

    @Autowired
    private ParkingSlotService parkingSlotService;

    @GetMapping("/find-nearest-parking")
    public List<ParkingSlotDTO> findNearestParking(
            @RequestParam Integer areaId,
            @RequestParam double longitude,
            @RequestParam double latitude,
            @RequestParam String time
    ) {
        LocalDateTime bookingTime = LocalDateTime.parse(time);

        return parkingSlotService.findNearestParking(areaId, longitude, latitude, bookingTime);
    }
}