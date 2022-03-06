package com.pet.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String name;

    @NotBlank
    @Column
    private String surname;

    @NotBlank
    @Column
    private Integer petId;

    @NotBlank
    @Column
    private String email;

    @NotBlank
    @Column
    private Date birthDate;

    @NotBlank
    @Column
    private String phoneNumber;

    @NotBlank
    @Column
    private String cpf;

    @NotBlank
    @Column
    private String address;

    @NotBlank
    @Column
    private String city;

    @NotBlank
    @Column
    private String country;

}
