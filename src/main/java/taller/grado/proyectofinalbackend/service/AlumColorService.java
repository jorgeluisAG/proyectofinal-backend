package taller.grado.proyectofinalbackend.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import taller.grado.proyectofinalbackend.model.AlumColors;
import taller.grado.proyectofinalbackend.repository.AlumColorsRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class AlumColorService {
    private final Logger log = LoggerFactory.getLogger(AlumColorService.class);

    private AlumColorsRepository alumColorsRepository;

    public List<AlumColors> getListAlumColors() {
        return alumColorsRepository.findAll();
    }

    public AlumColors getAlumColorById(Integer alumColorId){

        return alumColorsRepository.findById(alumColorId).orElse(null);

    }

}
