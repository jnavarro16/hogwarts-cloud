package com.jordi.hogwarts.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

//clase para la respuesta de API
public record EstudianteDTO(
        Long id,
        String nombre, //y apellido

        //validaciones
        @Min(1)
        @Max(7)
        @NotNull
        int anyoCurso,

        LocalDate fechaNacimiento,
        String casa,
        MascotaDTO mascota,
        List<AsignaturaCalificacionDTO> asignaturas
) {
}
