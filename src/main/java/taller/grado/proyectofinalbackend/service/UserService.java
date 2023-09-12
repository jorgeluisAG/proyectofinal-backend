package taller.grado.proyectofinalbackend.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import taller.grado.proyectofinalbackend.model.User;
import taller.grado.proyectofinalbackend.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    public User getUser(Integer userId){
        return userRepository.findById(userId).orElse(null);
    }
}
