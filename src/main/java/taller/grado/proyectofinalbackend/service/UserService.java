package taller.grado.proyectofinalbackend.service;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import taller.grado.proyectofinalbackend.model.Address;
import taller.grado.proyectofinalbackend.model.Person;
import taller.grado.proyectofinalbackend.model.User;
import taller.grado.proyectofinalbackend.model.dao.UserRequest;
import taller.grado.proyectofinalbackend.repository.PersonRepository;
import taller.grado.proyectofinalbackend.repository.UserRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private PersonRepository personaRepository;

    public User getUser(Integer userId){
        return userRepository.findById(userId).orElse(null);
    }

    public User createUser(UserRequest userRequest){

        Person persona = personaRepository.save(userRequest.getPerson());

        //Guardar Addres
        Address address = new Address();
        address.setIdAddress(1);
        address.setDescription("");
        User user = userRepository.findById(userRequest.getId()).get();

        user.setId(userRequest.getId());
        user.setPerson(persona);
        user.setNameUser(userRequest.getNameUser());
        user.setAddress(address);
        user.setPassword(userRequest.getPassword());
        user.setRol(userRequest.getRol());
        user.setMail(userRequest.getMail());
        User useraux = userRepository.save(user);
        log.info("DATOS OBTenidossssssssss",useraux);
        return useraux;
    }

    public List<User> getListUser() {
        return userRepository.findAll();
    }
}
