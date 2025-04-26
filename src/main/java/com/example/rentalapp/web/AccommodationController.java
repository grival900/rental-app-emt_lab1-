package com.example.rentalapp.web;

import com.example.rentalapp.dto.AccommodationDto;
import com.example.rentalapp.dto.CreateAccommodationDto;
import com.example.rentalapp.model.domain.Accommodation;
import com.example.rentalapp.service.application.AccommodationApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationApplicationService accommodationService;

    public AccommodationController(AccommodationApplicationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @Operation(summary = "Get all Accommodations")
    @GetMapping
    public ResponseEntity<List<AccommodationDto>> getAllAccommodations() {
        return ResponseEntity.ok(accommodationService.getAll());
    }

    @Operation(summary = "Get Accommodation by ID")
    @GetMapping("/{id}")
    public ResponseEntity<AccommodationDto> getAccommodationById(@PathVariable Long id) {
        Optional<AccommodationDto> accommodation = accommodationService.getById(id);
        return accommodation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new Accommodation")
    @PostMapping("/add")
    public ResponseEntity<AccommodationDto> createAccommodation(@RequestBody CreateAccommodationDto accommodation) {
        return accommodationService.create(accommodation).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing Accommodation")
    @PutMapping("/edit/{id}")
    public ResponseEntity<AccommodationDto> updateAccommodation(@PathVariable Long id, @RequestBody CreateAccommodationDto accommodation){
        return accommodationService.update(id, accommodation).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an Accommodation by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        if (accommodationService.getById(id).isPresent()){
            accommodationService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Mark an Accommodation as rented")
    @PutMapping("/{id}/rent")
    public ResponseEntity<AccommodationDto> markAsRented(@PathVariable Long id) {
        try {
            AccommodationDto updated = accommodationService.markAsRented(id);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
