package com.example.rentalapp.model.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private Host host;

    private Integer numRooms;

    private boolean rented;

    public Accommodation() {}

    public Accommodation(String name, Category category, Host host, Integer numRooms, boolean rented) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.rented = rented;
    }

    public Accommodation(Long id, String name, Category category, Host host, Integer numRooms, boolean rented) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.rented = rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }

    public boolean isRented() {
        return rented;
    }
}
