package taller.grado.proyectofinalbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import taller.grado.proyectofinalbackend.model.ProductColor;
import taller.grado.proyectofinalbackend.model.dto.ProductColorResponse;

import java.util.List;
import java.util.Optional;


public interface ProductColorRepository extends JpaRepository<ProductColor, Integer> {

    @Query("SELECT c.id, a.hex, a.colorName, c.stockColor FROM ProductColor c, AlumColors a, Product p " +
            "WHERE a.id = c.alumColor.id and c.product.id = p.id and c.product.id=:productColorId")
    List<Object[]> getProductsColors(Integer productColorId);

    @Query("SELECT c.alumColor.id, a.hex, a.colorName, c.stockColor FROM ProductColor c, AlumColors a, Product p " +
            "WHERE a.id = c.alumColor.id and c.product.id = p.id and c.product.id=:productColorId")
    List<Object[]> getProductsAlumColorsById(Integer productColorId);

    ProductColor findOneByProduct_IdAndAlumColor_Id(Integer productId,Integer alumColorId);

    public List<ProductColor> getAllByProductId(Integer productId);
}
