package taller.grado.proyectofinalbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "product_images")
public class ProductImages implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(name = "imageProduct", columnDefinition ="MEDIUMBLOB")
    private String imageProduct;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Product product;

}