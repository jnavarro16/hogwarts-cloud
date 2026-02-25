package com.jordi.hogwarts.Mapper;

import com.jordi.hogwarts.DTO.MascotaDTO;
import com.jordi.hogwarts.Model.Mascota;
import org.springframework.stereotype.Component;

@Component //registramos la clase para inytectar Spring
public class MascotaMapper
{
    public MascotaDTO toDTO(Mascota m) {
        if (m == null) return null;

        String amo = (m.getEstudiante() != null)
                ? MapperUtilidad.nombreCompleto(m.getEstudiante().getNombre(), m.getEstudiante().getApellido()) : null;
        return new MascotaDTO(
                m.getId() == null ? null : m.getId().longValue(),
                m.getNombre(),
                m.getEspecie(),
                amo
        );
    }
}
