package com.sofka.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.executable.ValidateOnExecution;

@Data
@ValidateOnExecution
@AllArgsConstructor
public class UpdateClietnDto {

    //NotBlank(message = "El nombre es requerido")
    @Size(min = 1, max = 50)
    private String name;

    //@NotBlank(message = "El apellido es requerido")
    @Size(min = 1, max = 50)
    private String surName;

    //@NotBlank(message = "El tipo de documento es requerido")
    @Size(min = 1, max = 10)
    private String documentType;

    //@NotBlank(message = "El documento es requerido")
    @Size(min = 1, max = 15)
    private String document;

    //@NotBlank(message = "El email es requerido")
    @Email(message = "El email no tiene el formato requerido requerido")
    private String email;

    //@NotNull(message = "La habitacion no puede ser nula")
    private Integer room;

    //@NotNull(message = "El precio no puede ser nula")
    private Integer price;
}
