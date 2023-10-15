package taller.grado.proyectofinalbackend.model.dao;

import lombok.Data;
import java.util.List;

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
    private Integer categoryId;
    private List<AlumColorStockRequest> alumColorStockRequests;
    private Integer thicknessId;

}


