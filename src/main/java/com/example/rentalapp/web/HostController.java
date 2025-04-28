package com.example.rentalapp.web;

import com.example.rentalapp.dto.CreateHostDto;
import com.example.rentalapp.dto.HostDto;
import com.example.rentalapp.model.domain.Host;
import com.example.rentalapp.model.projections.HostProjection;
import com.example.rentalapp.service.application.HostApplicationService;
import com.example.rentalapp.service.domain.HostService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hosts")
public class HostController {

    private final HostApplicationService hostService;

    public HostController(HostApplicationService hostService) {
        this.hostService = hostService;
    }

    @Operation(summary = "Get all Hosts")
    @GetMapping
    public List<HostDto> getAllHosts() {
        return hostService.getAll();
    }

    @Operation(summary = "Get Host by ID")
    @GetMapping("/{id}")
    public ResponseEntity<HostDto> getHostById(@PathVariable Long id) {
        Optional<HostDto> host = hostService.getById(id);
        return host.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new Host")
    @PostMapping("/add")
    public ResponseEntity<HostDto> createHost(@RequestBody CreateHostDto host) {
        return hostService.create(host).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing Host")
    @PutMapping("/edit/{id}")
    public ResponseEntity<HostDto> updateHost(@PathVariable Long id, @RequestBody CreateHostDto host) {
        return hostService.update(id, host).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a Host")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHost(@PathVariable Long id) {
        if(hostService.getById(id).isPresent()) {
            hostService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/hosts/names")
    public List<HostProjection> getHostNames() {
        return hostService.getHostNames();
    }
}