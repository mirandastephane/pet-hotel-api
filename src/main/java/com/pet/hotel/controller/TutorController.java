package com.pet.hotel.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pet.hotel.exception.ResourceNotFoundException;
import com.pet.hotel.model.Tutor;
import com.pet.hotel.repository.TutorRepository;

@RestController
@RequestMapping("/api")
@Api(value = "API REST HOTEL PET")
@CrossOrigin(origins = "*")
public class TutorController {

	@Autowired
	private TutorRepository tutorRepository;

	@GetMapping("/tutor")
	@ApiOperation(value = "Get All Tutors")
	public List<Tutor> getAllTutor() {
		return tutorRepository.findAll();
	}

	@PostMapping("/tutor")
	@ApiOperation(value = "Create a new Tutor")
	@ResponseStatus(HttpStatus.CREATED)
	public Tutor createTutor(@RequestBody Tutor tutor) {
		return (Tutor) tutorRepository.save(tutor);
	}

	@GetMapping("/tutor/{id}")
	@ApiOperation(value = "Get a Single Tutor")
	public Tutor getTutorById(@PathVariable(value = "id") Long tutorId) throws Throwable {
		return (Tutor) tutorRepository.findById(tutorId)
				.orElseThrow(() -> new ResourceNotFoundException("Tutor", "id", tutorId));
	}


	@PutMapping("/tutor/{id}")
	@ApiOperation(value = "Update a Tutor")
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


	@DeleteMapping("/tutor/{id}")
	@ApiOperation(value = "Delete a Tutor")
	public ResponseEntity<?> deleteTutor(@PathVariable(value = "id") Long tutorId) throws Throwable {
		Tutor tutor = (Tutor) tutorRepository.findById(tutorId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", tutorId));

		tutorRepository.delete(tutor);

		return ResponseEntity.ok().build();
	}
}
