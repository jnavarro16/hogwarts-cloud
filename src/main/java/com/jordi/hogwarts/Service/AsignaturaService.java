package com.jordi.hogwarts.Service;

import com.jordi.hogwarts.Repository.AsignaturaRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AsignaturaService {
    private final AsignaturaRepository asignaturaRepo;

    public AsignaturaService(AsignaturaRepository asignaturaRepo) {
        this.asignaturaRepo = asignaturaRepo;
    }

    @Transactional
    public void borrarPorId(Long id) {
        if(!asignaturaRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignatura no encontrada");
        }

        try {
            asignaturaRepo.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Asignatura existente");
        }
    }
}
