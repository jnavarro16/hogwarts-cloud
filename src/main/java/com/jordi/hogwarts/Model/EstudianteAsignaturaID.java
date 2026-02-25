package com.jordi.hogwarts.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class EstudianteAsignaturaID implements Serializable
{
    @Column(name = "id_estudiante")
    private Integer estudianteId;

    @Column(name = "id_asignatura")
    private Integer asignaturaId;
}
