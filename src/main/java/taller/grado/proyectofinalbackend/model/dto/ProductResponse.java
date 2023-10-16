package taller.grado.proyectofinalbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import taller.grado.proyectofinalbackend.model.Category;
import taller.grado.proyectofinalbackend.model.ProductColor;
import taller.grado.proyectofinalbackend.model.dao.AlumColorStockRequest;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponse {
    private Integer id;
    private String nameProduct;
    private String seriesProduct;
    private String imageProduct;
    private String descriptionProduct;
    private Integer stockTotal;
    private Double price;
    private Integer state;
    private Category category;
//    private List<ProductColor> productColors;
    private List<AlumColorStockRequest> alumColorStockRequests;
    //private Integer thicknessId;


    public ProductResponse() {

    }

}
