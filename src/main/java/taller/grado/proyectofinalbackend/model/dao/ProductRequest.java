package taller.grado.proyectofinalbackend.model.dao;

import lombok.Data;
import taller.grado.proyectofinalbackend.model.Category;
import taller.grado.proyectofinalbackend.model.ProductColor;
import taller.grado.proyectofinalbackend.model.Thickness;

@Data
public class ProductRequest {
    private Integer id;
    private String nameProduct;
    private String seriesProduct;
    private String imageProduct;
    private String descriptionProduct;
    private Integer stockTotal;
    private Double price;
    private Integer state;
    private Category category;
    private ProductColor productColor;
    private Thickness thickness;

}
