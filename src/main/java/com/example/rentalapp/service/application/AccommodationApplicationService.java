package com.example.rentalapp.service.application;

import com.example.rentalapp.dto.AccommodationDto;
import com.example.rentalapp.dto.CreateAccommodationDto;
import com.example.rentalapp.model.domain.Accommodation;
import com.example.rentalapp.model.domain.Host;
import com.example.rentalapp.service.domain.AccommodationService;
import com.example.rentalapp.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationService(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    public List<AccommodationDto> getAll(){
        return accommodationService.getAllAccommodations().stream().map(AccommodationDto::from).collect(Collectors.toList());
    }

    public Optional<AccommodationDto> getById(Long id) {
        return accommodationService.getAccommodationById(id).map(AccommodationDto::from);
    }

    public Optional<AccommodationDto> create(CreateAccommodationDto dto) {
        Optional<Host> host = hostService.getHostById(dto.hostId());

        return Optional.of(AccommodationDto.from(accommodationService.saveAccommodation(dto.toEntity(host.get()))));
    }

    public Optional<AccommodationDto> update(Long id, CreateAccommodationDto dto) {
        Accommodation existing = accommodationService.getAccommodationById(id).orElseThrow(()->new RuntimeException("No accommodation found"));

        existing.setName(dto.name());
        existing.setCategory(dto.category());
        existing.setNumRooms(dto.numRooms());
        existing.setHost(hostService.getHostById(dto.hostId()).orElseThrow(()->new RuntimeException("No host found")));

        return Optional.of(AccommodationDto.from(accommodationService.saveAccommodation(existing)));
    }

    public void delete(Long id) {
        accommodationService.deleteAccommodation(id);
    }

    public AccommodationDto markAsRented(Long id) {
        return AccommodationDto.from(accommodationService.markAsRented(id));
    }
}
