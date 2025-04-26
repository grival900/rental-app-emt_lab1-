package com.example.rentalapp.repository;

import com.example.rentalapp.model.domain.TemporaryReservation;
import com.example.rentalapp.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemporaryReservationRepository extends JpaRepository<TemporaryReservation, Long> {
    List<TemporaryReservation> findByUser(User user);
}