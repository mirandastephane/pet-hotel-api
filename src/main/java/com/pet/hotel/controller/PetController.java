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
    public Pet createPet(@Valid @RequestBody Pet pet) {
        return (Pet) petRepository.save(pet);
    }

    // Update a Pet
    @PutMapping("/pet/{id}")
    public Pet updatePet(@PathVariable(value = "id") Long petId,
                           @Valid @RequestBody Pet petDetails) throws Throwable {

        Pet pet = (Pet) petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", petId));

        pet.setId(petDetails.getId());
        pet.setName(petDetails.getName());
        pet.setBreed(petDetails.getBreed());
        pet.setSpecies(petDetails.getSpecies());
        pet.setAge(petDetails.getAge());
        pet.setTutorId(petDetails.getTutorId());

        Pet updatedPet = (Pet) petRepository.save(pet);
        return updatedPet;
    }

    // Delete a Pet
    @DeleteMapping("/pet/{id}")
    public ResponseEntity<?> deletePet(@PathVariable(value = "id") Long petId) throws Throwable {
        Pet pet = (Pet) petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", petId));

        petRepository.delete(pet);

        return ResponseEntity.ok().build();
    }
}
