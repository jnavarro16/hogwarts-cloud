package com.jordi.hogwarts.Service;

import com.jordi.hogwarts.Model.Estudiante;
import com.jordi.hogwarts.Model.Mascota;
import com.jordi.hogwarts.Repository.EstudianteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstudianteServiceTest
{
    @Mock
    private EstudianteRepository estudianteRepo;

    @InjectMocks
    private EstudianteService service;
    private Estudiante harryPotter;

    @BeforeEach
    void setUp() {
        harryPotter = new Estudiante();
        harryPotter.setId(1L);
        harryPotter.setNombre("Harry");
        harryPotter.setApellido("Potter");

        //mascota
        Mascota hedwig = new Mascota();
        hedwig.setId(1L);
        hedwig.setNombre("Hedwig");
        hedwig.setEspecie("Lechuza");
        hedwig.setEstudiante(harryPotter);

        harryPotter.setMascota(hedwig);
    }

    @Test
    void expulsarHarry_DeleteSeLlamaUnaVez()
    {
        //GIVEN
        Long idHarry = 1L;
        when (estudianteRepo.findById(idHarry)).thenReturn(Optional.of(harryPotter));

        //WHEN
        service.borrarPorId(idHarry);

        //THEN
        verify(estudianteRepo, times(1)).delete(harryPotter);
        verify(estudianteRepo, never()).deleteById(anyLong());
    }
//
//    @Test
//    void borrarPorId_siNoExisteDebeLanzar404()
//    {
//        //GIVEN
//        Long idInexistente = 999L;
//        when (estudianteRepo.findById(idInexistente)).thenReturn(Optional.empty());
//
//        //WHEN y THEN
//        assertThrows(ResponseStatusException.class, () -> service.borrarPorId(idInexistente));
//
//        //no debe llamar a DELETE
//        verify(estudianteRepo, times(1)).findById(idInexistente);
//        verify(estudianteRepo, never()).delete(any());
//    }
}
