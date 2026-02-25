package com.jordi.hogwarts.DTO;

public record AsignaturaDTO (
        long id,
        String nombre,
        String aula,
        Boolean obligatoria,
        String profesor
){
}
