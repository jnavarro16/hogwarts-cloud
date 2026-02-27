package com.jordi.hogwarts.Mapper;

import com.jordi.hogwarts.DTO.AsignaturaCalificacionDTO;
import com.jordi.hogwarts.DTO.EstudianteDTO;
import com.jordi.hogwarts.DTO.MascotaDTO;
import com.jordi.hogwarts.Model.Estudiante;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//convierte la entidad (Estudiante) en Respuesta (EstudianteDTO)
public class EstudianteMapper
{

    private final MascotaMapper mascotaMapper;
    public EstudianteMapper(MascotaMapper mascotaMapper){
        this.mascotaMapper = mascotaMapper;
    }

    public EstudianteDTO toDTO(Estudiante es) {
        if(es == null) return null;

        String nombreCompleto = MapperUtilidad.nombreCompleto(es.getNombre(), es.getApellido());
        String casa = (es.getCasa() != null ? es.getCasa().getNombre() : null);

        //tabla estudiante_asignatura con nota
        List<AsignaturaCalificacionDTO> asignaturas = (es.getMatriculas() == null) ? List.of() :
                es.getMatriculas().stream()
                        .map(matricula -> new AsignaturaCalificacionDTO(
                                matricula.getAsignatura() != null ? matricula.getAsignatura().getNombre() : null,
                                matricula.getCalificacion()
                        ))
                        .toList();

        MascotaDTO mascotaDto = null;
        try {
            mascotaDto = mascotaMapper.toDTO(es.getMascota());
        } catch (EntityNotFoundException ex) {
            mascotaDto = null;
        }

        return new EstudianteDTO(
                es.getId() == null ? 0L : es.getId(),
                nombreCompleto,
                es.getAnyoCurso(),
                es.getFechaNacimiento(),
                casa,
                mascotaDto,
                asignaturas
        );
    }
}
