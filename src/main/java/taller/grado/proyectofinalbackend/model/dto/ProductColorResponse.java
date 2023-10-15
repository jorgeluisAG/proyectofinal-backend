package taller.grado.proyectofinalbackend.model.dto;

import lombok.Data;

@Data
public class ProductColorResponse {
    private Integer id;
    private String hex;
    private String colorName;
    private String stockColor;
}
