package taller.grado.proyectofinalbackend.model.dao;

import taller.grado.proyectofinalbackend.model.Person;

import java.util.List;

public class UserAndAddressRequest {
    private Integer id;
    private String userName;
    private String email;
    private String password;
    private Boolean status;
    private Person person;
    private List<AddressRequest> addressRequests;
}
