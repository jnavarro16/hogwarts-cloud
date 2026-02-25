package com.jordi.hogwarts.DTO.Request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EstudianteUpdateDTO
{
    //si se intente mandar nombre/apellido en el JSON...error
    @Null(message = "No se permite modificar el nombre.")
    private String nombre;
    @Null(message = "No se permite modificar el apellido")
    private String apellido;

    @NotNull(message = "El anyo del Curso es obligatorio.")
    @Min(1)
    @Max(7)
    private Integer anyoCurso;

    @NotNull(message = "La fecha de Nacimiento no debe estar vacia.")
    private LocalDate fechaNacimiento;

    @Valid //validamos
    private MascotaCreateUpdateDTO mascota;
}
