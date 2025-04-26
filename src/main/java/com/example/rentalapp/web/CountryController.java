package com.example.rentalapp.web;

import com.example.rentalapp.dto.CountryDto;
import com.example.rentalapp.dto.CreateCountryDto;
import com.example.rentalapp.model.domain.Country;
import com.example.rentalapp.service.application.CountryApplicationService;
import com.example.rentalapp.service.domain.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryApplicationService countryService;

    public CountryController(CountryApplicationService countryService) {
        this.countryService = countryService;
    }

    @Operation(summary = "Get all Countries")
    @GetMapping
    public List<CountryDto> getAllCountries() {
        return countryService.getAll();
    }

    @Operation(summary = "Get Country by ID")
    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getCountryById(@PathVariable Long id) {
        Optional<CountryDto> country = countryService.getById(id);
        return country.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new Country")
    @PostMapping("/add")
    public ResponseEntity<CountryDto> createCountry(@RequestBody CreateCountryDto country) {
        return countryService.create(country).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing Country")
    @PutMapping("/edit/{id}")
    public ResponseEntity<CountryDto> updateCountry(@PathVariable Long id, @RequestBody CreateCountryDto country) {
        return countryService.update(id, country).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a Country by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        if(countryService.getById(id).isPresent()) {
            countryService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}