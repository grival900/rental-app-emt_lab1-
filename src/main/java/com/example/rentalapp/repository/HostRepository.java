package com.example.rentalapp.repository;

import com.example.rentalapp.model.domain.Host;
import com.example.rentalapp.model.projections.HostProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HostRepository extends JpaRepository<Host, Long> {
    Optional<Host> findByName(String name);

    List<HostProjection>findAllProjectedBy();
}
