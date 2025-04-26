package com.example.rentalapp.service.application;

import com.example.rentalapp.dto.CountryDto;
import com.example.rentalapp.dto.CreateCountryDto;
import com.example.rentalapp.model.domain.Country;
import com.example.rentalapp.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryApplicationService {

    private final CountryService service;

    public CountryApplicationService(CountryService service) {
        this.service = service;
    }

    public List<CountryDto> getAll() {
        return service.getAllCountries().stream()
                .map(CountryDto::from)
                .collect(Collectors.toList());
    }

    public Optional<CountryDto> getById(Long id) {
        return service.getCountryById(id).map(CountryDto::from);
    }

    public Optional<CountryDto> create(CreateCountryDto dto) {
        Country country = dto.toEntity();
        return Optional.of(CountryDto.from(service.saveCountry(country)));
    }

    public Optional<CountryDto> update(Long id, CreateCountryDto dto) {
        Country existing = service.getCountryById(id).orElseThrow(()->new RuntimeException("No country found"));
        existing.setName(dto.name());
        existing.setContinent(dto.continent());
        return Optional.of(CountryDto.from(service.saveCountry(existing)));
    }

    public void delete(Long id) {
        service.deleteCountry(id);
    }
}
