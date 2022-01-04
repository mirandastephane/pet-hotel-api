package com.pet.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pet.hotel.model.Tutor;
import com.pet.hotel.repository.TutorRepository;

@RestController
@RequestMapping("/tutor")
public class TutorController {
	
	@Autowired
	private TutorRepository tutorRepository;

	@GetMapping
	public List<Tutor> listar() {
		return tutorRepository.findAll();
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Tutor adicionar(@RequestBody Tutor tutor) {
		return tutorRepository.save(tutor);
	}
}
