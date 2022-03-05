package com.pet.hotel.controller;

import com.pet.hotel.exception.ResourceNotFoundException;
import com.pet.hotel.model.Reservation;
import com.pet.hotel.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/api")
public class ReservationController  {

    @Autowired
    ReservationRepository reservationRepository;

    // Get All Reservations
    @GetMapping("/reservations")
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Create a new Reservation
    @PostMapping("/reservations")
    public Reservation createReservation(@Valid @RequestBody Reservation reservation) {
        return (Reservation) reservationRepository.save(reservation);
    }

    // Get a Single Reservation
    @GetMapping("/reservations/{id}")
    public Reservation getReservationById(@PathVariable(value = "id") Long reservationId) throws Throwable {
        return (Reservation) reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));
    }

    // Update a Reservation
    @PutMapping("/reservations/{id}")
    public Reservation updateReservation(@PathVariable(value = "id") Long reservationId,
                           @Valid @RequestBody Reservation reservationDetails) throws Throwable {

        Reservation reservation = (Reservation) reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));

        reservation.setStartDate(reservationDetails.getStartDate());
        reservation.setEndDate(reservationDetails.getEndDate());
        reservation.setRoomId(reservationDetails.getRoomId());
        reservation.setPetId(reservationDetails.getPetId());
        reservation.setPrice(reservationDetails.getPrice());

        Reservation updatedReservation = (Reservation) reservationRepository.save(reservation);
        return updatedReservation;
    }

    // Delete a Reservation
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable(value = "id") Long reservationId) throws Throwable {
        Reservation reservation = (Reservation) reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));

        reservationRepository.delete(reservation);

        return ResponseEntity.ok().build();
    }
}
