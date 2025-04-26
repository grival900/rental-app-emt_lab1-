package com.example.rentalapp.dto;

import com.example.rentalapp.model.domain.*;

public record HostDto(
        Long id,
        String name,
        String surname,
        CountryDto country
) {
    public static HostDto from(Host h) {
        return new HostDto(
                h.getId(),
                h.getName(),
                h.getSurname(),
                CountryDto.from(h.getCountry())
        );
    }
}