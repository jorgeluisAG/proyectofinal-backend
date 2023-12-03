package taller.grado.proyectofinalbackend.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import taller.grado.proyectofinalbackend.model.Category;
import taller.grado.proyectofinalbackend.model.User;
import taller.grado.proyectofinalbackend.model.WorkCalendar;
import taller.grado.proyectofinalbackend.model.dao.WorkCalendarRequest;
import taller.grado.proyectofinalbackend.repository.UserRepository;
import taller.grado.proyectofinalbackend.repository.WorkCalendarRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Service
public class WorkCalendarService {

    private final Logger log = LoggerFactory.getLogger(WorkCalendarService.class);

    private WorkCalendarRepository workCalendarRepository;
    private UserRepository userRepository;

    public WorkCalendar createWorkCalendar(WorkCalendarRequest workCalendarRequest) {

        WorkCalendar workCalendar = new WorkCalendar();
        User user = userRepository.findById(Integer.parseInt(workCalendarRequest.getId())).get();
        workCalendar.setUser(user);
        workCalendar.setStatus(true);
        workCalendar.setCreatedAt(new Date());
        workCalendar.setDescriptionWork(workCalendarRequest.getTitle());
        SimpleDateFormat formatDate = new SimpleDateFormat("YYYY-MM-DD'T'HH:MM:SS");
        SimpleDateFormat formatDate2 = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
        System.out.println("DALEEE  ");
        System.out.println(workCalendarRequest.getStart());
//        try {
//            Date date = formatDate.parse(workCalendarRequest.getStart());
//            System.out.println(date);
            workCalendar.setStartDate(workCalendarRequest.getStart());
//            Date date1 = formatDate.parse(workCalendarRequest.getEnd());
            workCalendar.setEndDate(workCalendarRequest.getEnd());
            System.out.println("ENTROOOO");
//        } catch (ParseException e) {
//            e.printStackTrace();
//            System.out.println("NOOOO   ENTROOOO");
//        }
        workCalendarRepository.save(workCalendar);
        return workCalendar;
    }


    public List<WorkCalendarRequest> getListUserWorkCalendarById(Integer userId) {
        List<WorkCalendar> workCalendar = workCalendarRepository.findAll();
        List<WorkCalendarRequest> workCalendarRequests = new ArrayList<>();
        for (int i=0;i<workCalendar.size();i++){
            WorkCalendarRequest workCalendarRequest = new WorkCalendarRequest();
            if(workCalendar.get(i).getUser().getId()==userId){
                workCalendarRequest.setId(Integer.toString(workCalendar.get(i).getId()));
                workCalendarRequest.setTitle(workCalendar.get(i).getDescriptionWork());
                String pattern = "YYYY-MM-DD'T'HH:MM:SS";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//                String date = simpleDateFormat.format(workCalendar.get(i).getStartDate());
                workCalendarRequest.setStart(workCalendar.get(i).getStartDate());
//                String date2 = simpleDateFormat.format(workCalendar.get(i).getEndDate());
                workCalendarRequest.setEnd(workCalendar.get(i).getEndDate());
                workCalendarRequests.add(workCalendarRequest);
            }
        }
        return workCalendarRequests;
    }
}
