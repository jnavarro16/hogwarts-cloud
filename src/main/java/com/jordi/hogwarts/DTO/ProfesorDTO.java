package com.jordi.hogwarts.DTO;

import java.time.LocalDate;

public record ProfesorDTO (
        long id,
        String nombre, //y apellido
        String asignatura,
        LocalDate fechaInicio
){}
