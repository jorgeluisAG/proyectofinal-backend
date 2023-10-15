package taller.grado.proyectofinalbackend.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taller.grado.proyectofinalbackend.model.AlumColors;
import taller.grado.proyectofinalbackend.service.AlumColorService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/alumcolors")
public class AlumColorsController {
    private final Logger log =  LoggerFactory.getLogger(ProductController.class);

    private final AlumColorService alumColorService;

    @GetMapping("")
    public List<AlumColors> getAlumColorsList() {
        log.info("Get List alumColors");
        return alumColorService.getListAlumColors();
    }

    @GetMapping("{alumcolorId}")
    public AlumColors getAlumColorById(@PathVariable(name = "alumcolorId") Integer alumcolorId){
        log.info("AlumColors Info: " +  alumcolorId);
        return alumColorService.getAlumColorById(alumcolorId);
    }
}
