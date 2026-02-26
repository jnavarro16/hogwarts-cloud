package com.jordi.hogwarts.Service;

import com.jordi.hogwarts.Repository.AsignaturaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AsignaturaService {

    private final AsignaturaRepository asignaturaRepo;

    public AsignaturaService(AsignaturaRepository asignaturaRepo) {
        this.asignaturaRepo = asignaturaRepo;
    }

    @Transactional
    public void borrarPorId(Long id) {
        if (!asignaturaRepo.existsById(id)) {
            throw new EntityNotFoundException("Asignatura no encontrada con id: " + id);
        }

        try {
            asignaturaRepo.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("No se puede eliminar la asignatura: hay alumnos matriculados.");
        }
    }
}