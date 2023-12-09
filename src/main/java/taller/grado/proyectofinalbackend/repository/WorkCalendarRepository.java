package taller.grado.proyectofinalbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import taller.grado.proyectofinalbackend.model.WorkCalendar;

import java.util.List;

public interface WorkCalendarRepository extends JpaRepository<WorkCalendar, Integer> {

    List<WorkCalendar> findAllByStatusIsTrue();

}
