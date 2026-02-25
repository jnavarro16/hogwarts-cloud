package com.jordi.hogwarts.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

@Data
@Entity
@Table(name = "mascota")
@SoftDelete
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private Integer id;

    private String nombre;
    private String especie;

    @OneToOne
    @JoinColumn(name = "id_estudiante", nullable = false, unique = true)
    @JsonIgnoreProperties({"mascota", "matriculas", "casa"})//evitamos bucle
    private Estudiante estudiante;

    @JsonIgnoreProperties({"mascota", "asignaturas", "casa"})
    public void setEstudiante(final Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}
