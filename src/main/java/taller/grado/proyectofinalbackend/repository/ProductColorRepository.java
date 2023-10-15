package taller.grado.proyectofinalbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import taller.grado.proyectofinalbackend.model.ProductColor;
import taller.grado.proyectofinalbackend.model.dto.ProductColorResponse;

import java.util.List;


public interface ProductColorRepository extends JpaRepository<ProductColor, Integer> {

    @Query("SELECT a.hex, a.colorName, c.stockColor FROM ProductColor c, AlumColors a, Product p WHERE a.id = c.alumColor.id AND c.product.id = p.id AND c.product.id=:productColorId")
    List<ProductColorResponse> getProductColorsById(Integer productColorId);
}
