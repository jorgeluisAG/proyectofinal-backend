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
    private String sizeProduct;
    private Boolean status;
    private Integer categoryId;
    private List<AlumColorStockRequest> alumColorStockRequests;
    private List<AluminumSeriesRequest> aluminumSeriesRequests;
    private List<ProductImagesRequest> productImagesRequests;

    //private List<ThicknessRequest> thicknessRequests;
}


