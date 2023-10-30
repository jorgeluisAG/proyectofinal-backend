package taller.grado.proyectofinalbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import taller.grado.proyectofinalbackend.model.Category;
import taller.grado.proyectofinalbackend.model.ProductColor;
import taller.grado.proyectofinalbackend.model.dao.AlumColorStockRequest;
import taller.grado.proyectofinalbackend.model.dao.AluminumSeriesRequest;
import taller.grado.proyectofinalbackend.model.dao.ProductImagesRequest;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponse {
    private Integer id;
    private String nameProduct;
    private String descriptionProduct;
    private Integer stockTotal;
    private Double price;
    private Boolean status;
    private Category category;
    private List<ProductImagesRequest> productImagesRequests;
    private List<AluminumSeriesRequest> aluminumSeriesRequests;
    private List<AlumColorStockRequest> alumColorStockRequests;


    public ProductResponse() {

    }

}
