package com.example.rentalapp.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "hosts_by_country")
public class HostsByCountry {

    @Id
    private Long countryId;

    private Long hostsCount;

    public Long getCountryId() {
        return countryId;
    }

    public Long getHostsCount(){
        return hostsCount;
    }
}
