package com.jordi.hogwarts.Mapper;

import com.jordi.hogwarts.DTO.CasaDTO;
import com.jordi.hogwarts.Model.Casa;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CasaMapper
{
    private final ProfesorMapper profesorMapper;

    public CasaMapper(ProfesorMapper profesorMapper) {
        this.profesorMapper = profesorMapper;
    }

    public CasaDTO toDTO(Casa home) {
        if(home == null) return null;

        List<String> nombreEstudiantes = (home.getEstudiantes() == null) ? List.of() :
                home.getEstudiantes().stream()
                        .map(estudiante -> MapperUtilidad.nombreCompleto(estudiante.getNombre(), estudiante.getApellido()))
                        .toList();

        return new CasaDTO(
                home.getId() == null ? null : home.getId().longValue(),
                home.getNombre(),
                home.getFundador(),
                home.getFantasma(),
                profesorMapper.toDTO(home.getJefe()),
                nombreEstudiantes
        );
    }
}
