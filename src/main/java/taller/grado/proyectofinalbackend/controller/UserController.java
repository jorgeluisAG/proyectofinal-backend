package taller.grado.proyectofinalbackend.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import taller.grado.proyectofinalbackend.model.Address;
import taller.grado.proyectofinalbackend.model.User;
import taller.grado.proyectofinalbackend.model.dao.UserRequest;
import taller.grado.proyectofinalbackend.model.dto.ResetPasswordDTO;
import taller.grado.proyectofinalbackend.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final Logger log =  LoggerFactory.getLogger(RegisterController.class);

    private final UserService userService;

    @GetMapping("")
    public List<UserRequest> getUserList() {
        log.info("Get List user");
        return userService.getListUser();
    }

    @GetMapping("/only")
    public List<User> getUserOnlyList() {
        log.info("Get List user only");
        return userService.getListUserOnly();
    }

    @GetMapping("/address")
    public List<Address> getUserAddressList() {
        log.info("Get List user Address");
        return userService.getListAddress();
    }

    @GetMapping("/{userId}")
    public UserRequest getUserById(@PathVariable(name = "userId") Integer userId) {
        log.info("Get List userId: {}", userId);
        return userService.getUserById(userId);
    }

    @PostMapping("/confirm/")
    @ResponseStatus(HttpStatus.CREATED)
    public User creatUser(@RequestBody UserRequest userRequest, HttpServletRequest request){
        //log.info("Registrar usuario nuevo", userRequest);
        User usernew = userService.createUser(userRequest);
        //if (usernew!=null){
            //this.emailService.sendActivationEmail(user,new String[]{}, new HashMap<>() );
        //}
        return usernew;
    }

    @PutMapping("/change/")
    public User updateUser(@RequestBody UserRequest userRequest, HttpServletRequest request){
        log.info("Actualizar datos del usuario ", userRequest);
        System.out.println(userRequest);
        User userup = userService.updateUser(userRequest);
        return userup;
    }

    @PutMapping("/image/update")
    public User updateImageUser(@RequestBody User user){
        User usernew = userService.updateImageUser(user);
        return usernew;
    }

    @PutMapping("/password/update")
    public User updatePasswordUser(@RequestBody ResetPasswordDTO resetPasswordDTO){
        User userPass = userService.updatePasswordUser(resetPasswordDTO);
        return userPass;
    }
}
