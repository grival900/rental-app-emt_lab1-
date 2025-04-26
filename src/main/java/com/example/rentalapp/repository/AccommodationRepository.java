package com.example.rentalapp.repository;

import com.example.rentalapp.model.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
