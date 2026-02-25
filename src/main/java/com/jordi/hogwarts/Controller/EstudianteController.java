package com.jordi.hogwarts.Controller;

import com.jordi.hogwarts.DTO.EstudianteDTO;
import com.jordi.hogwarts.DTO.Request.EstudianteCreateDTO;
import com.jordi.hogwarts.DTO.Request.EstudianteUpdateDTO;
import com.jordi.hogwarts.Service.EstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService service;
    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    //GET Todos
    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> listar() {
        return ResponseEntity.ok(service.obtenerTodosDTO());
    }

    //GET ID
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> obtener(@PathVariable Long id) {
        return service.obtenerPorIdDTO(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //POST
    @PostMapping
    public ResponseEntity<EstudianteDTO> crear(@Valid @RequestBody EstudianteCreateDTO dto) {
        EstudianteDTO creado = service.crearConMascota(dto);

        return ResponseEntity
                .created(URI.create("/api/estudiantes/" + creado.id()))
                .body(creado);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> actualizar(@PathVariable Long id,
                                                    @Valid @RequestBody EstudianteUpdateDTO dto) {
        EstudianteDTO actualizado = service.actualizarTodo(id, dto);
        return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una asignatura por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Asignatura eliminada"),
            @ApiResponse(responseCode = "404", description = "Asignatura no encontrada"),
            @ApiResponse(responseCode = "409", description = "No se puede borrar: hay alumnos matriculados")
    })
    //DELETE

    public ResponseEntity<EstudianteDTO> eliminar(@PathVariable Long id) {
        service.borrarPorId(id);
        return ResponseEntity.noContent().build(); //204
    }
}
