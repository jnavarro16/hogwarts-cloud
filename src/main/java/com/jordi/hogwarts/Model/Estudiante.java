package com.jordi.hogwarts.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "estudiante")
@SoftDelete
//representa la tabla
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Integer id;

    private String nombre;
    private String apellido;

    @Column(name = "anyo_curso")
    private Integer anyoCurso;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "id_casa")
    @JsonIgnoreProperties({"estudiantes", "jefe"})
    private Casa casa;

    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"estudiante"})
    private Mascota mascota;

    @OneToMany(mappedBy = "estudiante")
    private java.util.List<EstudianteAsignatura> matriculas = new java.util.ArrayList<>();

    @JsonIgnoreProperties({"estudiantes", "jefe"})
    public void setCasa(final Casa casa) {
        this.casa = casa;
    }

    @JsonIgnoreProperties({"estudiante"})
    public void setMascota(final Mascota mascota) {
        this.mascota = mascota;
    }
}
