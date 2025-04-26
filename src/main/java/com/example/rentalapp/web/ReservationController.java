package com.example.rentalapp.web;

import com.example.rentalapp.dto.CreateReservationDto;
import com.example.rentalapp.dto.ReservationDto;
import com.example.rentalapp.service.application.ReservationApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationApplicationService reservationService;

    public ReservationController(ReservationApplicationService reservationService) {
        this.reservationService = reservationService;
    }

    @Operation(summary = "List temporary reservations for current user")
    @GetMapping
    public ResponseEntity<List<ReservationDto>> list(Principal principal) {
        return ResponseEntity.ok(reservationService.list(principal));
    }

    @Operation(summary = "Add an accommodation to temporary reservations")
    @PostMapping
    public ResponseEntity<ReservationDto> add(Principal principal,
                                              @RequestBody CreateReservationDto dto) {
        return ResponseEntity.ok(reservationService.add(principal, dto));
    }

    @Operation(summary = "Confirm all temporary reservations for current user")
    @PostMapping("/confirm")
    public ResponseEntity<Void> confirm(Principal principal) {
        reservationService.confirm(principal);
        return ResponseEntity.noContent().build();
    }
}