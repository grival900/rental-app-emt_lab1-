package com.example.rentalapp.web;

import com.example.rentalapp.model.Accommodation;
import com.example.rentalapp.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public List<Accommodation> getAllAccommodations() {
        return accommodationService.getAllAccommodations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> getAccommodationById(@PathVariable Long id) {
        Optional<Accommodation> accommodation = accommodationService.getAccommodationById(id);
        return accommodation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Accommodation createAccommodation(@RequestBody Accommodation accommodation) {
        return accommodationService.saveAccommodation(accommodation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        accommodationService.deleteAccommodation(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/rent")
    public ResponseEntity<Accommodation> markAsRented(@PathVariable Long id) {
        try {
            Accommodation updated = accommodationService.markAsRented(id);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
