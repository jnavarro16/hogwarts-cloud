package com.jordi.hogwarts.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "asignatura")
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignatura")
    private Long id;

    private String nombre;
    private String aula;
    private Boolean obligatoria;

    @OneToMany(mappedBy = "asignatura")
    private java.util.List<EstudianteAsignatura> matriculas = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "asignatura")
    @JsonIgnoreProperties({"asignatura"})
    private List<Profesor> profesores = new ArrayList<>();

    @JsonIgnoreProperties({"asignatura"})
    public void setProfesores(final List<Profesor> profesores) {
        this.profesores = profesores;
    }
}

