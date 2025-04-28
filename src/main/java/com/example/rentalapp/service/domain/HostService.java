package com.example.rentalapp.service.domain;

import com.example.rentalapp.events.HostChangedEvent;
import com.example.rentalapp.model.domain.Host;
import com.example.rentalapp.model.projections.HostProjection;
import com.example.rentalapp.repository.HostRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostService {
    private final HostRepository hostRepository;
    private final ApplicationEventPublisher publisher;

    public HostService(HostRepository hostRepository, ApplicationEventPublisher publisher) {
        this.hostRepository = hostRepository;
        this.publisher = publisher;
    }

    public List<Host> getAllHosts() {
        return hostRepository.findAll();
    }

    public Optional<Host> getHostById(Long id) {
        return hostRepository.findById(id);
    }

    public Host saveHost(Host host) {
        Host hostSaved = hostRepository.save(host);
        publisher.publishEvent(new HostChangedEvent(this));
        return hostSaved;
    }

    public void deleteHost(Long id) {
        hostRepository.deleteById(id);
        publisher.publishEvent(new HostChangedEvent(this));
    }

    public List<HostProjection> getHostNames() {
        return hostRepository.findAllProjectedBy();
    }
}