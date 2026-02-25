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
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService
{
    private final EstudianteRepository estudianteRepo;
    private final CasaRepository casaRepo;
    private final EstudianteMapper mapper;

    public EstudianteService(EstudianteRepository estudianteRepo, EstudianteMapper mapper,  CasaRepository casaRepo) {
        this.estudianteRepo = estudianteRepo;
        this.mapper = mapper;
        this.casaRepo = casaRepo;
    }

    //GET
    public List<EstudianteDTO> obtenerTodosDTO() {
        return estudianteRepo.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public Optional<EstudianteDTO> obtenerPorIdDTO(Long id) {
        return estudianteRepo.findById(id)
                .map(mapper::toDTO);
    }

    @Transactional
    public EstudianteDTO crearConMascota(EstudianteCreateDTO dto) {
        //Buscar la casa
        Casa casa = casaRepo.findById(dto.getCasaId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "El ID de la Casa No existe: " +dto.getCasaId()
                ));

        //2Crear estudiante
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        estudiante.setAnyoCurso(dto.getAnyoCurso());
        estudiante.setFechaNacimiento(dto.getFechaNacimiento());
        estudiante.setCasa(casa);

        //Crear mascota y conectarla
        Mascota m = new Mascota();
        m.setNombre(dto.getMascota().getNombre());
        m.setEspecie(dto.getMascota().getEspecie());
        m.setEstudiante(estudiante);

        estudiante.setMascota(m);

        //Guardamos
        Estudiante guardado = estudianteRepo.save(estudiante);

        return mapper.toDTO(guardado);
    }

    @Transactional
    public EstudianteDTO actualizarTodo(Long id, EstudianteUpdateDTO dto)
    {
        Estudiante estudiante = estudianteRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No existe estudiante con el ID: " +id
            ));

        //nombre y apellidos no se tocan
        estudiante.setAnyoCurso(dto.getAnyoCurso());
        estudiante.setFechaNacimiento(dto.getFechaNacimiento());

        //mascota
        if (dto.getMascota() == null) {
            estudiante.setMascota(null);
        } else {
            ponerMascota(estudiante, dto.getMascota());
        }

        Estudiante guardado = estudianteRepo.save(estudiante);
        return mapper.toDTO(guardado);
    }

    //metodo que decide que hacer con mascota
    private void ponerMascota(Estudiante e, MascotaCreateUpdateDTO mascotaDTO) {
        if(e.getMascota() == null) {
            Mascota nueva = new Mascota();
            nueva.setEstudiante(e);
            e.setMascota(nueva);
        }

        e.getMascota().setNombre(mascotaDTO.getNombre());
        e.getMascota().setEspecie(mascotaDTO.getEspecie());
    }

    //DELETE
    @Transactional
    public void borrarPorId(Long id) {
        Estudiante estudiante = estudianteRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe estudiante con el ID: " + id
                ));

        //softDelete  estudiante
        estudianteRepo.delete(estudiante);
    }
}
