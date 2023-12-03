package taller.grado.proyectofinalbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taller.grado.proyectofinalbackend.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
