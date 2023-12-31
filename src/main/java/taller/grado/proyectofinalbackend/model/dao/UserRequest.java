package taller.grado.proyectofinalbackend.model.dao;

import lombok.Data;
import taller.grado.proyectofinalbackend.model.Authority;
import taller.grado.proyectofinalbackend.model.Person;

import java.util.Date;
import java.util.List;

@Data
public class UserRequest {
    private Integer id;
    private String userName;
    private String email;
    private String password;
    private Boolean status;
    private Authority authority;
    private Boolean activated;
    private Date createdAt;
    private Date updatedAt;
    private Person person;
    private String imageUser;
    private List<AddressRequest> addressRequests;
}
