package taller.grado.proyectofinalbackend.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taller.grado.proyectofinalbackend.model.User;
import taller.grado.proyectofinalbackend.service.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final Logger log =  LoggerFactory.getLogger(RegisterController.class);

    private final UserService userService;

    @GetMapping("/{userId}")
    public User getUser(@PathVariable(name = "userId") Integer userId) {
        log.info("Get List Course Register userId: {}", userId);
        return userService.getUser(userId);
    }
}
