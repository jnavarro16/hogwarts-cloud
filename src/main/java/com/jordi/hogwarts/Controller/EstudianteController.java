package com.jordi.hogwarts.Controller;

import com.jordi.hogwarts.DTO.EstudianteDTO;
import com.jordi.hogwarts.DTO.Request.EstudianteCreateDTO;
import com.jordi.hogwarts.DTO.Request.EstudianteUpdateDTO;
import com.jordi.hogwarts.Service.EstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    // GET Todos
    @GetMapping
    @Operation(summary = "Obtiene todos los estudiantes")
    public ResponseEntity<List<EstudianteDTO>> listar() {
        List<EstudianteDTO> lista = service.obtenerTodosDTO();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.ok(lista); // 200
    }

    // GET ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un estudiante por su ID")
    public ResponseEntity<EstudianteDTO> obtener(@PathVariable Long id) {
        EstudianteDTO dto = service.obtenerPorIdDTO(id);
        return ResponseEntity.ok(dto); // 200
    }

    // POST
    @PostMapping
    @Operation(summary = "Crea un estudiante")
    public ResponseEntity<EstudianteDTO> crear(@Valid @RequestBody EstudianteCreateDTO dto) {
        EstudianteDTO creado = service.crearConMascota(dto);
        return ResponseEntity.status(201).body(creado); // 201

    }

    // PUT
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un estudiante")
    public ResponseEntity<EstudianteDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EstudianteUpdateDTO dto
    ) {
        EstudianteDTO actualizado = service.actualizarTodo(id, dto);
        return ResponseEntity.ok(actualizado); // 200
    }

    // DELETE
    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un estudiante por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.borrarPorId(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
