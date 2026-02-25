package com.jordi.hogwarts.Controller;
//comprobacion de que la API cumple con el codigo que es
import com.jordi.hogwarts.Service.AsignaturaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AsignaturaController.class)
public class AsignaturaControllerWebMvcTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AsignaturaService service;


    @Test
    void eliminar_siEsCorrecto_debeDevolver204() throws Exception {
        Long id = 1L;
        doNothing().when(service).borrarPorId(id);

        mockMvc.perform(delete("/api/asignaturas/{id}", id))
                .andExpect(status().isNoContent());
    }


    @Test
    void eliminar_siNoExiste_debeDevolver404() throws Exception {
        Long id = 999L;
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignatura no encontrada"))
                .when(service).borrarPorId(id);

        mockMvc.perform(delete("/api/asignaturas/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void eliminar_siTieneAlumnos_debeDevolver409() throws Exception {
        Long id = 2L;
        doThrow(new ResponseStatusException(HttpStatus.CONFLICT, "Asignatura existente"))
                .when(service).borrarPorId(id);

        mockMvc.perform(delete("/api/asignaturas/{id}", id))
                .andExpect(status().isConflict());
    }

}
