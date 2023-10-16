package taller.grado.proyectofinalbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductColorResponse {
    private Integer id;
    private String hex;
    private String colorName;
    private Integer stockColor;
}
