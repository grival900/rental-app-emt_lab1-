package com.example.rentalapp.dto;

import com.example.rentalapp.model.domain.Country;

public record CountryDto(
        Long id,
        String name,
        String continent
) {
    public static CountryDto from(Country c) {
        return new CountryDto(
                c.getId(),
                c.getName(),
                c.getContinent()
        );
    }
}