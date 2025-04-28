package com.example.rentalapp.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "accommodations_by_host")
public class AccommodationsByHost {

    @Id
    private Long hostId;

    private Long accommodationsCount;

    public Long getHostId() {
        return hostId;
    }

    public Long getAccommodationsCount() {
        return accommodationsCount;
    }
}