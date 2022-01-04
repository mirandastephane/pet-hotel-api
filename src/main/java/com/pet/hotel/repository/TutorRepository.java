package com.pet.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pet.hotel.model.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

}
