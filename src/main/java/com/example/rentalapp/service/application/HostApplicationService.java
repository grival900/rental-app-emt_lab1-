package com.example.rentalapp.service.application;

import com.example.rentalapp.dto.AccommodationDto;
import com.example.rentalapp.dto.CreateHostDto;
import com.example.rentalapp.dto.HostDto;
import com.example.rentalapp.model.domain.Country;
import com.example.rentalapp.model.domain.Host;
import com.example.rentalapp.service.domain.CountryService;
import com.example.rentalapp.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HostApplicationService {
    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationService(HostService hostService,
                                  CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    public List<HostDto> getAll() {
        return hostService.getAllHosts().stream()
                .map(HostDto::from)
                .collect(Collectors.toList());
    }

    public Optional<HostDto> getById(Long id) {
        return hostService.getHostById(id).map(HostDto::from);
    }

    public Optional<HostDto> create(CreateHostDto dto) {
        Optional<Country> country = countryService.getCountryById(dto.countryId());
        Host host = dto.toEntity(country.get());
        return Optional.of(HostDto.from(hostService.saveHost(host)));
    }

    public Optional<HostDto> update(Long id, CreateHostDto dto) {
        Host existing = hostService.getHostById(id).orElseThrow(()->new RuntimeException("No host found"));
        existing.setName(dto.name());
        existing.setSurname(dto.surname());
        existing.setCountry(countryService.getCountryById(dto.countryId()).orElseThrow(()->new RuntimeException("No country found")));
        return Optional.of(HostDto.from(hostService.saveHost(existing)));
    }

    public void delete(Long id) {
        hostService.deleteHost(id);
    }
}
