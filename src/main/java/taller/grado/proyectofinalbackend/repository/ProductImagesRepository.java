package taller.grado.proyectofinalbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taller.grado.proyectofinalbackend.model.ProductImages;

import java.util.List;

public interface ProductImagesRepository extends JpaRepository<ProductImages, Integer> {

    public List<ProductImages> getAllByProductId(Integer productId);
}
