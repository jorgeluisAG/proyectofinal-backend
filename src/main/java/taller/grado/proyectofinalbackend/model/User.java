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
    private String userName;

    @NotNull
    @Column(unique = true)
    private String email;


    private String password;

    private Boolean status;

    @ManyToOne(optional = false)
    @javax.validation.constraints.NotNull
    private Authority authority;

    @NotNull
    @Column(nullable = false)
    private Boolean activated = false;

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

    @Lob
    @Column(name = "imageUser", columnDefinition ="MEDIUMBLOB")
    private String imageUser;

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
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password);
        user.setStatus(status);
        user.setActivated(activated);
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt);
        user.setPerson(person);
        return user;
    }

}
