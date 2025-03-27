package com.example.rentalapp.service;

import com.example.rentalapp.model.Accommodation;
import com.example.rentalapp.repository.AccommodationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    public AccommodationService(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    public List<Accommodation> getAllAccommodations() {
        return accommodationRepository.findAll();
    }

    public Optional<Accommodation> getAccommodationById(Long id) {
        return accommodationRepository.findById(id);
    }

    public Accommodation saveAccommodation(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    public void deleteAccommodation(Long id) {
        accommodationRepository.deleteById(id);
    }

    public Accommodation markAsRented(Long id) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(id);
        if (accommodation.isPresent()) {
            Accommodation acc = accommodation.get();
            acc.setRented(true);
            return accommodationRepository.save(acc);
        } else {
            throw new RuntimeException("Accommodation not found");
        }
    }
}
