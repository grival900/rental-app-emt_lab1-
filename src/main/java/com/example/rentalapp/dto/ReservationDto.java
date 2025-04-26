package com.example.rentalapp.dto;

import com.example.rentalapp.model.domain.Accommodation;
import com.example.rentalapp.model.domain.TemporaryReservation;

public record ReservationDto(
        Long id,
        String userId,
        Long accommodationId,
        String accommodationName,
        boolean rented
) {
    public static ReservationDto from(TemporaryReservation tr) {
        Accommodation acc = tr.getAccommodation();
        return new ReservationDto(
                tr.getId(),
                tr.getUser().getId(),
                acc.getId(),
                acc.getName(),
                acc.isRented()
        );
    }
}