package taller.grado.proyectofinalbackend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idPerson;


    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String birthdate;

    private Integer status;

    private Integer idSeller;


}
