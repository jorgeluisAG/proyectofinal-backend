package taller.grado.proyectofinalbackend.service;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import taller.grado.proyectofinalbackend.model.Address;
import taller.grado.proyectofinalbackend.model.Authority;
import taller.grado.proyectofinalbackend.model.Person;
import taller.grado.proyectofinalbackend.model.User;
import taller.grado.proyectofinalbackend.model.dao.UserRequest;
import taller.grado.proyectofinalbackend.repository.AddressRepository;
import taller.grado.proyectofinalbackend.repository.PersonRepository;
import taller.grado.proyectofinalbackend.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private PersonRepository personaRepository;
    private AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUser(Integer userId){
        return userRepository.findById(userId).orElse(null);
    }

    public User createUser(UserRequest userRequest){

        Person persona = personaRepository.save(userRequest.getPerson());
        Address address = addressRepository.save(userRequest.getAddress());
        //Guardar Addres
        //Address address = new Address();
        //address.setIdAddress(1);
        //address.setDescription("");
        // PARA MANTENER LA FECHA DE CREACION
        //User user = userRepository.findById(userRequest.getId()).get();
        User user = new User();
        //user.setId(userRequest.getId());
        user.setNameUser(userRequest.getNameUser());
        System.out.println(passwordEncoder.encode(userRequest.getPassword()));
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setAuthority(new Authority("USER"));
        user.setActivated(true);
        user.setPerson(persona);
        user.setAddress(address);
        user.setEmail(userRequest.getEmail());
        user.setRol(userRequest.getRol());
        User useraux = userRepository.save(user);
        log.info("DATOS OBTenidossssssssss",useraux);
        return useraux;
    }

    public List<User> getListUser() {
        return userRepository.findAll();
    }

    public User updateUser(UserRequest userRequest){

        Person persona = personaRepository.save(userRequest.getPerson());
        Address address = addressRepository.save(userRequest.getAddress());
        //Guardar Addres
        //Address address = new Address();
        //address.setIdAddress(1);
        //address.setDescription("");
        // PARA MANTENER LA FECHA DE CREACION
        User user = userRepository.findById(userRequest.getId()).get();
        //User user = new User();
        user.setId(userRequest.getId());
        user.setNameUser(userRequest.getNameUser());
        user.setPassword(userRequest.getPassword());
        user.setPerson(persona);
        user.setAddress(address);
        user.setEmail(userRequest.getEmail());
        user.setRol(userRequest.getRol());
        User useraux = userRepository.save(user);
        log.info("DATOS OBTenidossssssssss",useraux);
        return useraux;
    }

    public Optional<User> getByEmail(String email ) {
        return userRepository.findOneByEmail(email);
    }

    public Optional<User> getByNameUser(String nameUser) {
        return userRepository.findOneByNameUser(nameUser);
    }
}
