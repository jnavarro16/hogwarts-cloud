package com.jordi.hogwarts.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MascotaCreateUpdateDTO
{
    @NotBlank(message = "El nombre de mascota no debe estar vacio.")
    private String nombre;

    @NotBlank(message = "La especie de la mascota no debe estar vacia.")
    private String especie;
}
