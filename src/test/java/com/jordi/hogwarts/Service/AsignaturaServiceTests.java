package com.jordi.hogwarts.Service;
// no se comprueba el FK, comprobacion de que Service responde bien
import com.jordi.hogwarts.Repository.AsignaturaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AsignaturaServiceTests {
    //mockito para finjir
    @Mock
    private AsignaturaRepository asignaturaRepo;

    @InjectMocks
    private AsignaturaService service;

    //si no existe
    @Test
    void borrarPorId_siNoExiste_debeLanzar404()
    {
        //Given
        Long id = 1L;
        when(asignaturaRepo.existsById(id)).thenReturn(false);

        //When
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> service.borrarPorId(id));

        //Then
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        verify(asignaturaRepo, never()).deleteById(any());
    }

    //si existe pero tiene alumnos
    @Test
    void borrarPorId_siTieneAlumnos_debeLanzar409() {
        //Given
        Long id = 1L;
        when(asignaturaRepo.existsById(id)).thenReturn(true);
        doThrow(new DataIntegrityViolationException("FK")).when(asignaturaRepo).deleteById(id);

        //When
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> service.borrarPorId(id));

        //Then
        assertEquals(HttpStatus.CONFLICT, ex.getStatusCode());
    }

    //si existe y no tiene alumnos
    @Test
    void borrarPorId_siExisteYSinAlumnos_debeBorrar() {
        //Given
        Long id = 1L;
        when(asignaturaRepo.existsById(id)).thenReturn(true);

        //When
        assertDoesNotThrow(() -> service.borrarPorId(id));

        //Then
        verify(asignaturaRepo).deleteById(id);
    }

}
