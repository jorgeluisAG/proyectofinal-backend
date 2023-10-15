package taller.grado.proyectofinalbackend.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taller.grado.proyectofinalbackend.model.Thickness;
import taller.grado.proyectofinalbackend.service.ThicknessService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/thickness")
public class ThicknessController {
    private final Logger log =  LoggerFactory.getLogger(ThicknessController.class);

    private final ThicknessService thicknessService;

    @GetMapping("")
    public List<Thickness> getThicknessList() {
        log.info("Get List Thickness");
        return thicknessService.getListThickness();
    }

    @GetMapping("{thicknessId}")
    public Thickness getThicknessById(@PathVariable(name = "thicknessId") Integer thicknessId){
        log.info("AlumColors Info: " +  thicknessId);
        return thicknessService.getThicknessById(thicknessId);
    }
}