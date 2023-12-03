package taller.grado.proyectofinalbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taller.grado.proyectofinalbackend.model.AlumColors;

import java.util.Optional;

public interface AlumColorsRepository extends JpaRepository<AlumColors, Integer> {

    // findOneByUserName(String nameUser);
    Optional<AlumColors> findOneByColorName(String colorName);
}
