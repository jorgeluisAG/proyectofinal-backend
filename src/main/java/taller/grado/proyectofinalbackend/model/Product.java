package taller.grado.proyectofinalbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String nameProduct;

    private Double price;

    private Integer stockTotal;

    private String descriptionProduct;

    private String sizeProduct;

    private Boolean status;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Category category;

}
