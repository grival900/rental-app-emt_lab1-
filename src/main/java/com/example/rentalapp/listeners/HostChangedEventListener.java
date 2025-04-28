package com.example.rentalapp.listeners;

import com.example.rentalapp.events.HostChangedEvent;
import com.example.rentalapp.repository.HostsByCountryRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostChangedEventListener {

    private final HostsByCountryRepository repository;

    public HostChangedEventListener(HostsByCountryRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void handleHostChanged(HostChangedEvent event) {
        repository.refreshMaterializedView();
    }
}
