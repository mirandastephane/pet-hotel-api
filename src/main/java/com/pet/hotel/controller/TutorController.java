package com.pet.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pet.hotel.exception.ResourceNotFoundException;
import com.pet.hotel.model.Tutor;
import com.pet.hotel.repository.TutorRepository;

@RestController
@RequestMapping("/api")
public class TutorController {

	@Autowired
	private TutorRepository tutorRepository;

	// Get All Tutors
	@GetMapping("/tutor")
	public List<Tutor> getAllTutor() {
		return tutorRepository.findAll();
	}

	// Create a new Tutor
	@PostMapping("/tutor")
	@ResponseStatus(HttpStatus.CREATED)
	public Tutor createTutor(@RequestBody Tutor tutor) {
		return (Tutor) tutorRepository.save(tutor);
	}

	// Get a Single Tutor
	@GetMapping("/tutor/{id}")
	public Tutor getTutorById(@PathVariable(value = "id") Long tutorId) throws Throwable {
		return (Tutor) tutorRepository.findById(tutorId)
				.orElseThrow(() -> new ResourceNotFoundException("Tutor", "id", tutorId));
	}

	// Update a Tutor
	@PutMapping("/tutor/{id}")
	public Tutor updateTutor(@PathVariable(value = "id") Long tutorId, @RequestBody Tutor tutorDetails)
			throws Throwable {

		Tutor tutor = (Tutor) tutorRepository.findById(tutorId)
				.orElseThrow(() -> new ResourceNotFoundException("Tutor", "id", tutorId));

		tutor.setNome(tutorDetails.getNome());
		tutor.setDataNascimento(tutorDetails.getDataNascimento());
		tutor.setCpf(tutorDetails.getCpf());

		Tutor updatedTutor = (Tutor) tutorRepository.save(tutor);
		return updatedTutor;
	}

	// Delete a Tutor
	@DeleteMapping("/tutor/{id}")
	public ResponseEntity<?> deleteTutor(@PathVariable(value = "id") Long tutorId) throws Throwable {
		Tutor tutor = (Tutor) tutorRepository.findById(tutorId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", tutorId));

		tutorRepository.delete(tutor);

		return ResponseEntity.ok().build();
	}
}
