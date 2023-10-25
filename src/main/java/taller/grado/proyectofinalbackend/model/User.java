package taller.grado.proyectofinalbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Entity
@ToString
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String nameUser;

    @NotNull
    @Column(unique = true)
    private String email;


    private String password;

    @NotNull
    private String rol;

    @ManyToOne(optional = false)
    @javax.validation.constraints.NotNull
    private Authority authority;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Person person;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Address address;


    @PrePersist
    protected void prePersist() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public User toUser(){
        User user = new User();
        user.setNameUser(nameUser);
        user.setEmail(email);
        user.setPassword(password);
        user.setRol(rol);
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt);
        user.setPerson(person);
        user.setAddress(address);
        return user;
    }
}
