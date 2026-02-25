package com.jordi.hogwarts.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Entity
@Table(
        name = "profesor"
)
public class Profesor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    private Integer id;

    private String nombre;
    private String apellido;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @ManyToOne
    @JoinColumn(name = "id_asignatura")
    @JsonIgnoreProperties({"profesores", "matriculas"})
    private Asignatura asignatura;

    @OneToOne(mappedBy = "jefe")
    @JsonIgnoreProperties({"jefe", "estudiantes"})
    private Casa casaJefeDe;

    @JsonIgnoreProperties({"profesor"})
    public void setAsignatura(final Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    @JsonIgnoreProperties({"jefe", "estudiantes"})
    public void setCasaJefeDe(final Casa casaJefeDe) {
        this.casaJefeDe = casaJefeDe;
    }
}
