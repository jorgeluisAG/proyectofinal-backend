package taller.grado.proyectofinalbackend.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taller.grado.proyectofinalbackend.service.RegisterService;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/register")
public class RegisterController {
    private final Logger log =  LoggerFactory.getLogger(RegisterController.class);

    private final RegisterService registerService;


}
