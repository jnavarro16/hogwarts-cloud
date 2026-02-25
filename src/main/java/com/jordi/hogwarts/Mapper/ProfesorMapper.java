package com.jordi.hogwarts.Mapper;

import com.jordi.hogwarts.DTO.ProfesorDTO;
import com.jordi.hogwarts.Model.Profesor;
import org.springframework.stereotype.Component;

@Component
public class ProfesorMapper {
    public ProfesorDTO toDTO(Profesor profe) {
        if(profe == null) return null;

        String nombreCompleto = MapperUtilidad.nombreCompleto(profe.getNombre(), profe.getApellido());
        String asignatura = (profe.getAsignatura() != null) ? profe.getAsignatura().getNombre() : null;

        return new ProfesorDTO(
                profe.getId() == null ? null : profe.getId().longValue(),
                nombreCompleto,
                asignatura,
                profe.getFechaInicio()
        );
    }
}
