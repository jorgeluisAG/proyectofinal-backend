package taller.grado.proyectofinalbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import taller.grado.proyectofinalbackend.model.ProductAlumSeries;

import java.util.List;

public interface ProductAlumSeriesRepository extends JpaRepository<ProductAlumSeries, Integer> {

    @Query("SELECT s.id, a.seriesProduct FROM Product p, AluminumSeries a, ProductAlumSeries s " +
            "WHERE a.id = s.aluminumSeries.id and p.id = s.product.id and s.product.id = :productSeriesId")
    List<Object[]> getProductsSeries(Integer productSeriesId);

}
