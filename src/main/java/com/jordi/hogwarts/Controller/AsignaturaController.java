package com.jordi.hogwarts.Controller;

import com.jordi.hogwarts.Service.AsignaturaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaController {

    private final AsignaturaService service;

    public AsignaturaController(AsignaturaService service) {
        this.service = service;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una asignatura por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.borrarPorId(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
