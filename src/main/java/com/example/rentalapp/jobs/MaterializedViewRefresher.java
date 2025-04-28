package com.example.rentalapp.jobs;

import com.example.rentalapp.repository.AccommodationsByHostRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MaterializedViewRefresher {
    private final AccommodationsByHostRepository repository;

    public MaterializedViewRefresher(AccommodationsByHostRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        repository.refreshMaterializedView();
    }

    // every day at midnight
    @Scheduled(cron = "0 0 0 * * *")
    public void refreshAccommodationsByHost() {
        repository.refreshMaterializedView();
    }
}
