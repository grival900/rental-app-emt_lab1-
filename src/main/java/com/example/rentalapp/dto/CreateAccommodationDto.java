package com.example.rentalapp.dto;

import com.example.rentalapp.model.domain.*;

public record CreateAccommodationDto(
        String name,
        Category category,
        Long hostId,
        Integer numRooms
) {
    public Accommodation toEntity(Host host) {
        Accommodation accommodation = new Accommodation();
        accommodation.setName(this.name);
        accommodation.setCategory(this.category);
        accommodation.setHost(host);
        accommodation.setNumRooms(this.numRooms);
        return accommodation;
    }
}