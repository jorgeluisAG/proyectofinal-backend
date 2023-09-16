package taller.grado.proyectofinalbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taller.grado.proyectofinalbackend.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
