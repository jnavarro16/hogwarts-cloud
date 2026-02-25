package com.jordi.hogwarts.Repository;

import com.jordi.hogwarts.Model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Integer> {}
