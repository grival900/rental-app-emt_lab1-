package com.example.rentalapp.service.domain;

import com.example.rentalapp.model.domain.Accommodation;
import com.example.rentalapp.model.domain.TemporaryReservation;
import com.example.rentalapp.model.domain.User;
import com.example.rentalapp.model.exceptions.AccommodationNotFoundException;
import com.example.rentalapp.repository.AccommodationRepository;
import com.example.rentalapp.repository.TemporaryReservationRepository;
import com.example.rentalapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationService {
    private final TemporaryReservationRepository tempRepo;
    private final AccommodationRepository accRepo;
    private final UserRepository userRepo;

    public ReservationService(TemporaryReservationRepository tempRepo,
                                    AccommodationRepository accRepo,
                                    UserRepository userRepo) {
        this.tempRepo = tempRepo;
        this.accRepo = accRepo;
        this.userRepo = userRepo;
    }

    public List<TemporaryReservation> listByUser(String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return tempRepo.findByUser(user);
    }

    @Transactional
    public TemporaryReservation add(String userId, Long accommodationId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Accommodation acc = accRepo.findById(accommodationId)
                .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));
        if (acc.isRented()) {
            throw new RuntimeException("Accommodation is already rented");
        }
        TemporaryReservation tr = new TemporaryReservation();
        tr.setUser(user);
        tr.setAccommodation(acc);
        return tempRepo.save(tr);
    }

    @Transactional
    public void confirmAll(String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<TemporaryReservation> list = tempRepo.findByUser(user);
        for (TemporaryReservation tr : list) {
            Accommodation acc = tr.getAccommodation();
            if (acc.isRented()) {
                throw new RuntimeException("Accommodation " + acc.getId() + " already rented");
            }
            acc.setRented(true);
            accRepo.save(acc);
            tempRepo.delete(tr);
        }
    }
}