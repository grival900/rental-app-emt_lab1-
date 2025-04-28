package com.example.rentalapp.config;

import com.example.rentalapp.model.domain.*;
import com.example.rentalapp.model.enumerations.Role;
import com.example.rentalapp.repository.AccommodationRepository;
import com.example.rentalapp.repository.CountryRepository;
import com.example.rentalapp.repository.HostRepository;
import com.example.rentalapp.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            CountryRepository countryRepository,
            HostRepository hostRepository,
            UserRepository userRepository,
            AccommodationRepository accommodationRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.userRepository = userRepository;
        this.accommodationRepository = accommodationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
//        countryRepository.save(new Country("Texas", "N. America"));
//        countryRepository.save(new Country("Houston", "N. America"));
//        countryRepository.save(new Country("Macedonia", "Europe"));
//
//        hostRepository.save(new Host("Danilo", "Ivanov", countryRepository.findByName("Macedonia").orElseThrow()));
//        hostRepository.save(new Host("Olinad", "Evilov", countryRepository.findByName("Texas").orElseThrow()));
//        hostRepository.save(new Host("Oled", "Ekran", countryRepository.findByName("Houston").orElseThrow()));
//
//        accommodationRepository.save(new Accommodation("Trapot", Category.HOUSE, hostRepository.findByName("Danilo").orElseThrow(), 2, false));
//        accommodationRepository.save(new Accommodation("Sopiste", Category.HOTEL, hostRepository.findByName("Olinad").orElseThrow(), 6, false));
//        accommodationRepository.save(new Accommodation("Boba", Category.APARTMENT, hostRepository.findByName("Oled").orElseThrow(), 1, true));

        userRepository.save(new User(
                "admin",
                passwordEncoder.encode("admin"),
                "ADMIN",
                "admin",
                Role.ROLE_ADMIN
        ));

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_USER
        ));

        userRepository.save(new User(
                "host",
                passwordEncoder.encode("host"),
                "host",
                "host",
                Role.ROLE_HOST
        ));
    }
}
