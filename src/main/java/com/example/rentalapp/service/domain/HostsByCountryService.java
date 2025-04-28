package com.example.rentalapp.service.domain;


import com.example.rentalapp.model.views.HostsByCountry;
import com.example.rentalapp.repository.HostsByCountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostsByCountryService {
    private final HostsByCountryRepository hostRepository;

    public HostsByCountryService(HostsByCountryRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    public List<HostsByCountry> getAll() {
        return hostRepository.findAll();
    }

    public void refreshHostsByCountry() {
        hostRepository.refreshMaterializedView();
    }
}
