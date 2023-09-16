package taller.grado.proyectofinalbackend.model.dao;

import lombok.Data;
import taller.grado.proyectofinalbackend.model.Address;
import taller.grado.proyectofinalbackend.model.Person;

@Data
public class UserRequest {
    private Integer id;
    private String nameUser;
    private String mail;
    private String password;
    private String rol;
    private Person person;
    private Address address;
}
