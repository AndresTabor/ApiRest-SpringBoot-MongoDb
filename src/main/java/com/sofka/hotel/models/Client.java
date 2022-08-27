package com.sofka.hotel.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

@Data
@Document(collection = "client")
public class Client {
    @Id
    private String id;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 1, max = 50)
    private String name;


    @NotBlank(message = "El apellido es requerido")
    @Size(min = 1, max = 50)
    private String surName;

    @NotBlank(message = "El tipo de documento es requerido")
    @Size(min = 1, max = 10)
    private String documentType;

    @NotBlank(message = "El documento es requerido")
    @Size(min = 1, max = 15)
    private String document;

    @NotBlank(message = "El email es requerido")
    @Email
    private String email;

    @NotBlank(message = "El lugar de procedencia es requerida")
    @Size(min = 1, max = 50)
    private String comesFrom;

    @NotNull(message ="La fecha es requerida")
    @DateTimeFormat(pattern = "yyyy-mm-ddT00:00:00.000+00:00")
    private Date date;

    @NotNull(message = "La habitacion no puede ser nula")
    private Integer room;

    @NotNull(message = "El precio no puede ser nula")
    private Integer price;

    @NotNull(message = "El state no puede ser nula")
    private Integer state;

    public Client() {
    }

    public Client(String id, String name, String surName, String documentType, String document, String email, String comesFrom, Date date, Integer room, Integer price, Integer state) {
        this.id = Objects.requireNonNull(id);
        this.name = name;
        this.surName = surName;
        this.documentType = documentType;
        this.document = document;
        this.email = email;
        this.comesFrom = comesFrom;
        this.date = date;
        this.room = room;
        this.price = price;
        this.state = state;
    }
}
