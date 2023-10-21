package taller.grado.proyectofinalbackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "images")
public class Images implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(name = "images", columnDefinition ="MEDIUMBLOB")
    private String images;

}