package taller.grado.proyectofinalbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taller.grado.proyectofinalbackend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findOneByEmailAndActivatedIsTrue(String email);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByUserName(String nameUser);

    List<User> findAllByActivatedIsTrue();
}
