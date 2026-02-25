package com.jordi.hogwarts.DTO.Request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class EstudianteCreateDTO
{
    @NotBlank(message = "El nombre es obligatorio·")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio.")
    private String apellido;

    @NotNull(message = "El anyoCurso no debe estar vacio.")
    //validacion
    @Min(value = 1, message = "El anyo del Curso debe estar entre 1 y 7")
    @Max(value = 7, message = "EL anyo del Curso debe estar entre 1 y 7")
    private Integer anyoCurso;

    @NotNull(message = "La fechaNacimiento no debe estar vacio.")
    //validacion
    @Past(message = "La fecha de nacimiento debe ser pasada.")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El ID de la casa es obligatorio. ")
    private Integer casaId;

    @NotNull(message = "La mascota es obligatoria en el POST")
    @Valid
    private MascotaCreateUpdateDTO mascota;
}
