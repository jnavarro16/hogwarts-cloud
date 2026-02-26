package com.jordi.hogwarts.Service;

import com.jordi.hogwarts.DTO.EstudianteDTO;
import com.jordi.hogwarts.DTO.Request.EstudianteCreateDTO;
import com.jordi.hogwarts.DTO.Request.EstudianteUpdateDTO;
import com.jordi.hogwarts.DTO.Request.MascotaCreateUpdateDTO;
import com.jordi.hogwarts.Mapper.EstudianteMapper;
import com.jordi.hogwarts.Model.Casa;
import com.jordi.hogwarts.Model.Estudiante;
import com.jordi.hogwarts.Model.Mascota;
import com.jordi.hogwarts.Repository.CasaRepository;
import com.jordi.hogwarts.Repository.EstudianteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepo;
    private final CasaRepository casaRepo;
    private final EstudianteMapper mapper;

    public EstudianteService(EstudianteRepository estudianteRepo, EstudianteMapper mapper, CasaRepository casaRepo) {
        this.estudianteRepo = estudianteRepo;
        this.mapper = mapper;
        this.casaRepo = casaRepo;
    }

    // GET todos
    public List<EstudianteDTO> obtenerTodosDTO() {
        return estudianteRepo.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    // GET por id
    public EstudianteDTO obtenerPorIdDTO(Long id) {
        Estudiante estudiante = estudianteRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id));
        return mapper.toDTO(estudiante);
    }

    @Transactional
    public EstudianteDTO crearConMascota(EstudianteCreateDTO dto) {

        Casa casa = casaRepo.findById(Long.valueOf(dto.getCasaId()))
                .orElseThrow(() -> new IllegalArgumentException("El ID de la Casa no existe: " + dto.getCasaId()));

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        estudiante.setAnyoCurso(dto.getAnyoCurso());
        estudiante.setFechaNacimiento(dto.getFechaNacimiento());
        estudiante.setCasa(casa);

        Mascota m = new Mascota();
        m.setNombre(dto.getMascota().getNombre());
        m.setEspecie(dto.getMascota().getEspecie());
        m.setEstudiante(estudiante);

        estudiante.setMascota(m);

        Estudiante guardado = estudianteRepo.save(estudiante);
        return mapper.toDTO(guardado);
    }

    @Transactional
    public EstudianteDTO actualizarTodo(Long id, EstudianteUpdateDTO dto) {

        Estudiante estudiante = estudianteRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id));

        estudiante.setAnyoCurso(dto.getAnyoCurso());
        estudiante.setFechaNacimiento(dto.getFechaNacimiento());

        if (dto.getMascota() == null) {
            estudiante.setMascota(null);
        } else {
            ponerMascota(estudiante, dto.getMascota());
        }

        Estudiante guardado = estudianteRepo.save(estudiante);
        return mapper.toDTO(guardado);
    }

    private void ponerMascota(Estudiante e, MascotaCreateUpdateDTO mascotaDTO) {
        if (e.getMascota() == null) {
            Mascota nueva = new Mascota();
            nueva.setEstudiante(e);
            e.setMascota(nueva);
        }
        e.getMascota().setNombre(mascotaDTO.getNombre());
        e.getMascota().setEspecie(mascotaDTO.getEspecie());
    }

    @Transactional
    public void borrarPorId(Long id) {
        Estudiante estudiante = estudianteRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id));

        estudianteRepo.delete(estudiante);
    }
}