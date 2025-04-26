package com.example.rentalapp.service.application;

import com.example.rentalapp.dto.CreateReservationDto;
import com.example.rentalapp.dto.ReservationDto;
import com.example.rentalapp.model.domain.TemporaryReservation;
import com.example.rentalapp.service.domain.ReservationService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationApplicationService {
    private final ReservationService domainService;

    public ReservationApplicationService(ReservationService domainService) {
        this.domainService = domainService;
    }

    public List<ReservationDto> list(Principal principal) {
        String userId = principal.getName();
        return domainService.listByUser(userId).stream()
                .map(ReservationDto::from)
                .collect(Collectors.toList());
    }

    public ReservationDto add(Principal principal, CreateReservationDto dto) {
        String userId = principal.getName();
        TemporaryReservation tr = domainService.add(userId, dto.accommodationId());
        return ReservationDto.from(tr);
    }

    public void confirm(Principal principal) {
        String userId = principal.getName();
        domainService.confirmAll(userId);
    }
}