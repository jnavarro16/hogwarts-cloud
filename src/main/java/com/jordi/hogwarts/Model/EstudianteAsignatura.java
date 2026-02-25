package com.jordi.hogwarts.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "estudiante_asignatura")
public class EstudianteAsignatura
{
    @EmbeddedId
    private EstudianteAsignaturaID id;

    @ManyToOne
    @MapsId("estudianteId")
    @JoinColumn(name = "id_estudiante")
    @JsonIgnore
    private Estudiante estudiante;

    @ManyToOne
    @MapsId("asignaturaId")
    @JoinColumn(name = "id_asignatura")
    private Asignatura asignatura;

    private Double calificacion;//campo extra
}
