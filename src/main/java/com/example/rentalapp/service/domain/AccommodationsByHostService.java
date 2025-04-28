package com.example.rentalapp.service.domain;

import com.example.rentalapp.model.views.AccommodationsByHost;
import com.example.rentalapp.repository.AccommodationsByHostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationsByHostService {

    private final AccommodationsByHostRepository repository;

    public AccommodationsByHostService(AccommodationsByHostRepository repository) {
        this.repository = repository;
    }

    public List<AccommodationsByHost> getByHost() {
        return repository.findAll();
    }

    public void refreshAccommodationsByHost() {
        repository.refreshMaterializedView();
    }
}