package com.example.rentalapp.dto;

import com.example.rentalapp.model.domain.*;

public record CreateCountryDto(
        String name,
        String continent
) {
    public Country toEntity() {
        Country country = new Country();
        country.setName(this.name);
        country.setContinent(this.continent);
        return country;
    }
}