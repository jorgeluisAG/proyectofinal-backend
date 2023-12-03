package taller.grado.proyectofinalbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "work_calendar")
public class WorkCalendar implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "YYYY-MM-DD'T'HH:MM:SS")
    private Date startDate;

    @JsonFormat(pattern = "YYYY-MM-DD'T'HH:MM:SS")
    private Date endDate;

    private String descriptionWork;

    private Boolean status;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @PrePersist
    protected void prePersist() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

}
