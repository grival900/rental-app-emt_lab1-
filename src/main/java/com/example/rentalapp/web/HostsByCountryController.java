package com.example.rentalapp.web;

import com.example.rentalapp.model.views.HostsByCountry;
import com.example.rentalapp.service.domain.HostsByCountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HostsByCountryController {

    private final HostsByCountryService service;

    public HostsByCountryController(HostsByCountryService service) {
        this.service = service;
    }

    @GetMapping("/api/hosts/by-country")
    public List<HostsByCountry> getHostsByCountry() {
//        service.refreshHostsByCountry();
        return service.getAll();
    }
}
