package taller.grado.proyectofinalbackend.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import taller.grado.proyectofinalbackend.model.User;
import taller.grado.proyectofinalbackend.model.dao.UserRequest;
import taller.grado.proyectofinalbackend.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final Logger log =  LoggerFactory.getLogger(RegisterController.class);

    private final UserService userService;

    @GetMapping("")
    public List<User> getUserList() {
        log.info("Get List user");
        return userService.getListUser();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable(name = "userId") Integer userId) {
        log.info("Get List userId: {}", userId);
        return userService.getUser(userId);
    }

    @PostMapping("/confirm/")
    @ResponseStatus(HttpStatus.CREATED)
    public User creatUser(@RequestBody UserRequest userRequest, HttpServletRequest request){
        log.info("Registrar usuario nuevo", userRequest);
        User usernew = userService.createUser(userRequest);
        if (usernew!=null){
            //this.emailService.sendActivationEmail(user,new String[]{}, new HashMap<>() );
        }
        return usernew;
    }
}
