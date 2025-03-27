package com.example.rentalapp.repository;

import com.example.rentalapp.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
