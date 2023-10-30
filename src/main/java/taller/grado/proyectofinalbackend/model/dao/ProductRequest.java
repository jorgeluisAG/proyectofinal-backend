package taller.grado.proyectofinalbackend.model.dao;

import lombok.Data;
import java.util.List;

@Data
public class ProductRequest {
    private Integer id;
    private String nameProduct;
    private String descriptionProduct;
    private Integer stockTotal;
    private Double price;
    private Boolean status;
    private Integer categoryId;
    private List<ProductImagesRequest> productImagesRequests;
    private List<AluminumSeriesRequest> aluminumSeriesRequests;
    private List<AlumColorStockRequest> alumColorStockRequests;
    //private List<ThicknessRequest> thicknessRequests;
}


