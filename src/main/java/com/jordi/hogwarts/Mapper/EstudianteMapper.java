package com.jordi.hogwarts.Mapper;

import com.jordi.hogwarts.DTO.AsignaturaCalificacionDTO;
import com.jordi.hogwarts.DTO.EstudianteDTO;
import com.jordi.hogwarts.Model.Estudiante;
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

        return new EstudianteDTO(
                es.getId() == null ? null : es.getId().longValue(),
                nombreCompleto,
                es.getAnyoCurso(),
                es.getFechaNacimiento(),
                casa,
                mascotaMapper.toDTO(es.getMascota()),
                asignaturas
        );
    }
}
