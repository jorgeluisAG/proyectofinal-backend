package taller.grado.proyectofinalbackend.model.dao;

import lombok.Data;

import java.util.Date;

@Data
public class WorkCalendarRequest {
    private String id;
    private Date start;
    private Date end;
    private String title;
}
