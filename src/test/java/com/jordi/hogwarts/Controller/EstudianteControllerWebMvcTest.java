package com.jordi.hogwarts.Controller;

import com.jordi.hogwarts.Service.EstudianteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EstudianteController.class)
public class EstudianteControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstudianteService estudianteService;

    @Test
    void crearEstudiante_conAnyoCurso10_debeDevolver400() throws Exception {
        //GIVEN
        String cuerpo = """
                {
                "nombre": "Test",
                "apellido": "Alumno",
                "anyoCurso": 10,
                "fechaNacimiento": "1983-11-15",
                "casaId": 2,
                "mascota": {
                     "nombre": "Ninbus",
                     "especie": "Lechuza"
                 }
                }
                """;

        //WHEN y THEN
        mockMvc.perform(post("/api/estudiantes")
                .contentType((MediaType.APPLICATION_JSON))
                .content(cuerpo))
                .andExpect(status().isBadRequest());

    }
}
