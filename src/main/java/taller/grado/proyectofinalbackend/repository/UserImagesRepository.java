package taller.grado.proyectofinalbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taller.grado.proyectofinalbackend.model.Images;

public interface UserImagesRepository extends JpaRepository<Images, Integer> {
}
