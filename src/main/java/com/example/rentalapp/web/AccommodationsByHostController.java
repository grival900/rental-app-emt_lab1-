package com.example.rentalapp.web;


import com.example.rentalapp.model.views.AccommodationsByHost;
import com.example.rentalapp.service.domain.AccommodationsByHostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccommodationsByHostController {

    private final AccommodationsByHostService service;

    public AccommodationsByHostController(AccommodationsByHostService service) {
        this.service = service;
    }

    @GetMapping("/api/accommodations/by-host")
    public List<AccommodationsByHost> getAccommodationsByHost() {
        // CHECK za da ne se update sekoj pat na get zakomentiraj linija pod (ke se update samo sekoj den kako sto e def vo jobs.MaterialzedViewRefresher
        service.refreshAccommodationsByHost();
        return service.getByHost();
    }
}