package taller.grado.proyectofinalbackend.model.dao;

import lombok.Data;

@Data
public class AlumColorStockRequest {
    private Integer id;
    private String hex;
    private String colorName;
    private Integer stockColor;
}
