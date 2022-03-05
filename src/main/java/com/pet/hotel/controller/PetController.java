package com.pet.hotel.controller;

import com.pet.hotel.exception.ResourceNotFoundException;
import com.pet.hotel.model.Pet;
import com.pet.hotel.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/api")
public class PetController {
    @Autowired
    PetRepository petRepository;

    // Get All Pets
    @GetMapping("/pet")
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // Get a Single Pet
    @GetMapping("/pet/{id}")
    public Pet getPetById(@PathVariable(value = "id") Long petId) throws Throwable {
        return (Pet) petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", petId));
    }

    // Create a new Pet
    @PostMapping("/pet")
    public Pet createPet(@Valid @RequestBody Pet guest) {
        return (Pet) petRepository.save(guest);
    }

    // Update a Pet
    @PutMapping("/pet/{id}")
    public Pet updatePet(@PathVariable(value = "id") Long petId,
                           @Valid @RequestBody Pet guestDetails) throws Throwable {

        Pet guest = (Pet) petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", petId));

        guest.setName(guestDetails.getName());
        guest.setSurname(guestDetails.getSurname());
        guest.setEmail(guestDetails.getEmail());
        guest.setAddress(guestDetails.getAddress());
        guest.setCity(guestDetails.getCity());
        guest.setCountry(guestDetails.getCountry());
        guest.setPersonalId(guestDetails.getPersonalId());
        guest.setPhoneNumber(guestDetails.getPhoneNumber());

        Pet updatedPet = (Pet) petRepository.save(guest);
        return updatedPet;
    }

    // Delete a Pet
    @DeleteMapping("/pet/{id}")
    public ResponseEntity<?> deletePet(@PathVariable(value = "id") Long petId) throws Throwable {
        Pet guest = (Pet) petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", petId));

        petRepository.delete(guest);

        return ResponseEntity.ok().build();
    }
}
