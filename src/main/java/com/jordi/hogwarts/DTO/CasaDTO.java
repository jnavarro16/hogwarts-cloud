package com.jordi.hogwarts.DTO;

import java.util.List;

public record CasaDTO(
        long id,
        String nombre,
        String fundador,
        String fantasma,
        ProfesorDTO jefe,
        List<String> estudiantes
) {
}
