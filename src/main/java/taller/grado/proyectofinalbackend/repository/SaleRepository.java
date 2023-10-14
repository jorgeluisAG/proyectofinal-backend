package taller.grado.proyectofinalbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taller.grado.proyectofinalbackend.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
