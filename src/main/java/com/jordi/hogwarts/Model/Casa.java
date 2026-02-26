package com.jordi.hogwarts.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(
        name = "casa"
)
public class Casa {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id_casa"
    )
    private Long id;
    private String nombre;
    private String fundador;
    private String fantasma;
    @OneToMany(
            mappedBy = "casa"
    )
    @JsonIgnoreProperties({"casa"})
    private List<Estudiante> estudiantes;
    @OneToOne
    @JoinColumn(
            name = "id_jefe"
    )
    @JsonIgnoreProperties({"casaJefeDe"})
    private Profesor jefe;

    @JsonIgnoreProperties({"casa"})
    public void setEstudiantes(final List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @JsonIgnoreProperties({"casaJefeDe"})
    public void setJefe(final Profesor jefe) {
        this.jefe = jefe;
    }
}

