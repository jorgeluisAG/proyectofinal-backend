package taller.grado.proyectofinalbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taller.grado.proyectofinalbackend.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
