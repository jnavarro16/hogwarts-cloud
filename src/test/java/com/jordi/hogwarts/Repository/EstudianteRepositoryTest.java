package com.jordi.hogwarts.Repository;

import com.jordi.hogwarts.Model.Estudiante;
import com.jordi.hogwarts.Model.Mascota;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
public class EstudianteRepositoryTest
{
    @Autowired
    private EstudianteRepository estudianteRepo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void eliminarEstudiante_DebeEliminarMascotaEnCascada()
    {
        //GIVEN
        //creo estudiante y mascota
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Test");
        estudiante.setApellido("Estudiante");
        estudiante.setAnyoCurso(5);

        Mascota mascota = new Mascota();
        mascota.setNombre("Test");
        mascota.setEspecie("Perro");
        mascota.setEstudiante(estudiante);
        estudiante.setMascota(mascota);

        entityManager.persist(estudiante);
        entityManager.flush();
        entityManager.clear();

        Long idMascota = mascota.getId().longValue();

        //WHEN
        //borro estudiante
        estudianteRepo.delete(estudiante);
        estudianteRepo.flush();

        //THEN
        //la mascota desaparece
        Mascota mascotaEnBD = entityManager.find(Mascota.class, idMascota);
        assertNull(mascotaEnBD);

    }
}
