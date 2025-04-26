package com.example.rentalapp.service.domain;

import com.example.rentalapp.model.domain.Host;
import com.example.rentalapp.repository.HostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostService {
    private final HostRepository hostRepository;

    public HostService(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    public List<Host> getAllHosts() {
        return hostRepository.findAll();
    }

    public Optional<Host> getHostById(Long id) {
        return hostRepository.findById(id);
    }

    public Host saveHost(Host host) {
        return hostRepository.save(host);
    }

    public void deleteHost(Long id) {
        hostRepository.deleteById(id);
    }
}