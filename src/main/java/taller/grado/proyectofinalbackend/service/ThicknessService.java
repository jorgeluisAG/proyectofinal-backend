package taller.grado.proyectofinalbackend.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import taller.grado.proyectofinalbackend.model.Thickness;
import taller.grado.proyectofinalbackend.repository.ThicknessRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ThicknessService {
    private final Logger log = LoggerFactory.getLogger(AlumColorService.class);

    private ThicknessRepository thicknessRepository;

    public List<Thickness> getListThickness() {
        return thicknessRepository.findAll();
    }

    public Thickness getThicknessById(Integer thicknessId){

        return thicknessRepository.findById(thicknessId).orElse(null);

    }

}
