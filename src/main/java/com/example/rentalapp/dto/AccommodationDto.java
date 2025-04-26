package com.example.rentalapp.dto;

import com.example.rentalapp.model.domain.*;

public record AccommodationDto(
        Long id,
        String name,
        Category category,
        HostDto host,
        Integer numRooms,
        boolean rented
) {
    public static AccommodationDto from(Accommodation a) {
        return new AccommodationDto(
                a.getId(),
                a.getName(),
                a.getCategory(),
                HostDto.from(a.getHost()),
                a.getNumRooms(),
                a.isRented()
        );
    }
}