package taller.grado.proyectofinalbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taller.grado.proyectofinalbackend.model.Product;
import taller.grado.proyectofinalbackend.model.ProductImages;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    //findAllByActivatedIsTrue();
    List<Product> findAllByStatusIsTrue();

    //ProductImages findOneByProduct_Id(Integer productId);
}
