package taller.grado.proyectofinalbackend.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import taller.grado.proyectofinalbackend.model.WorkCalendar;

import taller.grado.proyectofinalbackend.model.dao.UserRequest;
import taller.grado.proyectofinalbackend.model.dao.WorkCalendarRequest;

import taller.grado.proyectofinalbackend.service.WorkCalendarService;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/v1/calendar")
public class WorkCalendarController {

    private final Logger log =  LoggerFactory.getLogger(WorkCalendarController.class);

    private final WorkCalendarService workCalendarService;


    @PostMapping("/confirm/")
    @ResponseStatus(HttpStatus.CREATED)
    public WorkCalendar createWorkCalendar(@RequestBody WorkCalendarRequest workCalendarRequest){
        WorkCalendar workCalendar = workCalendarService.createWorkCalendar(workCalendarRequest);
        return workCalendar;
    }

    @GetMapping("/list/{userId}")
    public List<WorkCalendarRequest> getListUserWorkCalendarById(@PathVariable(name = "userId") Integer userId) {
        log.info("Get List userId: {} " , userId);
        return workCalendarService.getListUserWorkCalendarById(userId);
    }

//    @GetMapping("/{userId}")
//    public WorkCalendarRequest getWorkCalendarById(@PathVariable(name = "userId") Integer userId) {
//        log.info("Get List userId: {}", userId);
//        return workCalendarService.getWorkCalendarById(userId);
//    }

}
