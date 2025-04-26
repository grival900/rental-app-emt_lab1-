package com.example.rentalapp.dto;

import com.example.rentalapp.model.domain.*;

public record CreateHostDto(
        String name,
        String surname,
        Long countryId
) {
    public Host toEntity(Country country) {
        Host host = new Host();
        host.setName(this.name);
        host.setSurname(this.surname);
        host.setCountry(country);
        return host;
    }
}