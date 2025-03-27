package com.example.rentalapp.web;

import com.example.rentalapp.model.Host;
import com.example.rentalapp.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hosts")
public class HostController {
    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    public List<Host> getAllHosts() {
        return hostService.getAllHosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Host> getHostById(@PathVariable Long id) {
        Optional<Host> host = hostService.getHostById(id);
        return host.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Host createHost(@RequestBody Host host) {
        return hostService.saveHost(host);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHost(@PathVariable Long id) {
        hostService.deleteHost(id);
        return ResponseEntity.noContent().build();
    }
}