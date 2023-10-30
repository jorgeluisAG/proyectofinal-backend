package taller.grado.proyectofinalbackend.model.dao;

import lombok.Data;

@Data
public class AddressRequest {

    private Integer id;
    private String description;
    private Boolean status;
}
