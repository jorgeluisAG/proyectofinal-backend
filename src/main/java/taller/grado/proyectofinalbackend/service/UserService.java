package taller.grado.proyectofinalbackend.service;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import taller.grado.proyectofinalbackend.model.*;
import taller.grado.proyectofinalbackend.model.dao.AddressRequest;
import taller.grado.proyectofinalbackend.model.dao.UserRequest;
import taller.grado.proyectofinalbackend.model.dto.ResetPasswordDTO;
import taller.grado.proyectofinalbackend.repository.AddressRepository;
import taller.grado.proyectofinalbackend.repository.AuthorityRepository;
import taller.grado.proyectofinalbackend.repository.PersonRepository;
import taller.grado.proyectofinalbackend.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private PersonRepository personaRepository;
    private AddressRepository addressRepository;
    private AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUser(Integer userId){
        return userRepository.findById(userId).orElse(null);
    }

    public User createUser(UserRequest userRequest){

        //User user = userRepository.findById(userRequest.getId()).get();
        Person persona = personaRepository.save(userRequest.getPerson());
        //Address address = addressRepository.save(userRequest.getAddress());
        //Guardar Addres
        //Address address1 = new Address();
        //address1.setId(1);
        //address1.setDescription("");
        //Address address = addressRepository.save(address1);
        // PARA MANTENER LA FECHA DE CREACION
        //User user = userRepository.findById(userRequest.getId()).get();
        User user = new User();
        //user.setId(userRequest.getId());
        user.setUserName(userRequest.getUserName());
        //user.setCreatedAt(new Date());
        //System.out.println(passwordEncoder.encode(userRequest.getPassword()));
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setAuthority(new Authority("USER"));
        user.setActivated(true);
        user.setPerson(persona);
        user.setEmail(userRequest.getEmail());
        user.setStatus(true);
        User useraux = userRepository.save(user);
        log.info("DATOS OBTenidossssssssss ",useraux);
        //log.info("DATOS ADRRES: {} ",address);
        return useraux;
    }

    public List<UserRequest> getListUser() {

        List<User> users = userRepository.findAllByActivatedIsTrue();
        System.out.println(users.get(0).getStatus());
        List<Address> addresses = addressRepository.findAll();
        List<UserRequest> userRequests = new ArrayList<>();
        for (User user : users) {

            List<AddressRequest> addressRequestList = new ArrayList<>();
            UserRequest userRequest = new UserRequest();
            for (Address address : addresses) {
                AddressRequest addressRequest = new AddressRequest();
                if(address.getUser()!=null){
                    if (user.getId() == address.getUser().getId()) {
                        addressRequest.setId(address.getId());
                        addressRequest.setDescription(address.getDescription());
                        addressRequest.setStatus(address.getStatus());
                        addressRequestList.add(addressRequest);
                    }
                }
            }
            userRequest.setAddressRequests(addressRequestList);

            //log.info("DATOS ID ==== " + users.get(i).getId());
            //log.info("DATOS ID REQUEST ==== " + userRequests.size());
            userRequest.setId(user.getId());
            //log.info("DATOS ID REQUEST ==== " + userRequests.get(i).getId());
            userRequest.setUserName(user.getUserName());
            userRequest.setEmail(user.getEmail());
            userRequest.setPassword(user.getPassword());
            userRequest.setStatus(user.getStatus());
            userRequest.setAuthority(user.getAuthority());
            userRequest.setActivated(user.getActivated());
//            userRequests.get(i).setActivated(users.get(i).getActivated());
            userRequest.setCreatedAt(user.getCreatedAt());
            userRequest.setUpdatedAt(user.getUpdatedAt());
            userRequest.setImageUser(user.getImageUser());
            userRequest.setPerson(user.getPerson());
            userRequests.add(userRequest);
        }

        return userRequests;
    }

    public List<UserRequest> getUserEmployeesList() {

        List<User> users1 = userRepository.findAllByActivatedIsTrue();
        System.out.println(users1.get(0).getStatus());
        List<Address> addresses = addressRepository.findAll();
        List<UserRequest> userRequests = new ArrayList<>();
        for (User user : users1) {

            if(user.getAuthority().getId().equals("PERSONAL")){
                List<AddressRequest> addressRequestList = new ArrayList<>();
                UserRequest userRequest = new UserRequest();
                for (Address address : addresses) {
                    AddressRequest addressRequest = new AddressRequest();
                    if(address.getUser()!=null){
                        if (user.getId() == address.getUser().getId()) {
                            addressRequest.setId(address.getId());
                            addressRequest.setDescription(address.getDescription());
                            addressRequest.setStatus(address.getStatus());
                            addressRequestList.add(addressRequest);
                        }
                    }
                }
                userRequest.setAddressRequests(addressRequestList);

                userRequest.setId(user.getId());
                userRequest.setUserName(user.getUserName());
                userRequest.setEmail(user.getEmail());
                userRequest.setPassword(user.getPassword());
                userRequest.setStatus(user.getStatus());
                userRequest.setAuthority(user.getAuthority());
                userRequest.setActivated(user.getActivated());
                userRequest.setCreatedAt(user.getCreatedAt());
                userRequest.setUpdatedAt(user.getUpdatedAt());
                userRequest.setImageUser(user.getImageUser());
                userRequest.setPerson(user.getPerson());
                userRequests.add(userRequest);
            }

        }

        return userRequests;
    }



    public List<Address> getListAddress(){
        return addressRepository.findAll();
    }

    public User updateUser(UserRequest userRequest){
        User user = userRepository.findById(userRequest.getId()).get();
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setImageUser(userRequest.getImageUser());
        user.setUpdatedAt(new Date());
        user.setAuthority(userRequest.getAuthority());

        Person person1 = personaRepository.findById(user.getPerson().getId()).get();
        person1.setLastName(userRequest.getPerson().getLastName());
        person1.setFirstName(userRequest.getPerson().getFirstName());
        person1.setPhoneNumber(userRequest.getPerson().getPhoneNumber());
        person1.setDocumentNumber(userRequest.getPerson().getDocumentNumber());
        person1.setBirthdate(userRequest.getPerson().getBirthdate());
        person1.setSex(userRequest.getPerson().getSex());
        Person persona = personaRepository.save(person1);
        user.setPerson(persona);
        User useraux = userRepository.save(user);
        System.out.println("ENTROSSSSSSSSS" + userRequest.getAddressRequests().get(0).getDescription());
        List<Address> address = addressRepository.findAllByUserId(useraux.getId());
        if(address.size()>0){
            address.get(0).setDescription(userRequest.getAddressRequests().get(0).getDescription());
            addressRepository.save(address.get(0));
        }else {
            Address address1 = new Address();
            address1.setDescription(userRequest.getAddressRequests().get(0).getDescription());
            address1.setStatus(true);
            address1.setUser(useraux);
            addressRepository.save(address1);
        }
        log.info("DATOS OBTenidossssssssss",useraux);
        return useraux;
    }

    public User updateUserEmployee(UserRequest userRequest) {
        User user = userRepository.findById(userRequest.getId()).get();
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setImageUser(userRequest.getImageUser());
        user.setUpdatedAt(new Date());
        user.setAuthority(userRequest.getAuthority());

        Person personOne = personaRepository.findById(user.getPerson().getId()).get();
        personOne.setLastName(userRequest.getPerson().getLastName());
        personOne.setFirstName(userRequest.getPerson().getFirstName());
        personOne.setPhoneNumber(userRequest.getPerson().getPhoneNumber());
        personOne.setDocumentNumber(userRequest.getPerson().getDocumentNumber());
        personOne.setBirthdate(userRequest.getPerson().getBirthdate());
        personOne.setSex(userRequest.getPerson().getSex());
        Person persona = personaRepository.save(personOne);
        user.setPerson(persona);
        User useraux = userRepository.save(user);
        System.out.println("ENTROSSSSSSSSS" + userRequest.getAddressRequests().get(0).getDescription());
        List<Address> address = addressRepository.findAllByUserId(useraux.getId());
        if(address.size()>0){
            address.get(0).setDescription(userRequest.getAddressRequests().get(0).getDescription());
            addressRepository.save(address.get(0));
        }else {
            Address address1 = new Address();
            address1.setDescription(userRequest.getAddressRequests().get(0).getDescription());
            address1.setStatus(true);
            address1.setUser(useraux);
            addressRepository.save(address1);
        }
        log.info("DATOS OBTenidossssssssss",useraux);
        return useraux;
    }

    public User updateUserCustomer(UserRequest userRequest) {
        User user1 = userRepository.findById(userRequest.getId()).get();
        user1.setUserName(userRequest.getUserName());
        user1.setEmail(userRequest.getEmail());
        user1.setImageUser(userRequest.getImageUser());
        user1.setUpdatedAt(new Date());
        user1.setAuthority(userRequest.getAuthority());

        Person personOne = personaRepository.findById(user1.getPerson().getId()).get();
        personOne.setLastName(userRequest.getPerson().getLastName());
        personOne.setFirstName(userRequest.getPerson().getFirstName());
        personOne.setPhoneNumber(userRequest.getPerson().getPhoneNumber());
        personOne.setDocumentNumber(userRequest.getPerson().getDocumentNumber());
        personOne.setBirthdate(userRequest.getPerson().getBirthdate());
        personOne.setSex(userRequest.getPerson().getSex());
        Person persona = personaRepository.save(personOne);
        user1.setPerson(persona);
        User useraux = userRepository.save(user1);
        System.out.println("ENTROSSSSSSSSS" + userRequest.getAddressRequests().get(0).getDescription());
        List<Address> address = addressRepository.findAllByUserId(useraux.getId());
        if(address.size()>0){
            address.get(0).setDescription(userRequest.getAddressRequests().get(0).getDescription());
            addressRepository.save(address.get(0));
        }else {
            Address address1 = new Address();
            address1.setDescription(userRequest.getAddressRequests().get(0).getDescription());
            address1.setStatus(true);
            address1.setUser(useraux);
            addressRepository.save(address1);
        }
        log.info("DATOS OBTenidossssssssss",useraux);
        return useraux;
    }

    public Optional<User> getByEmail(String email ) {
        return userRepository.findOneByEmail(email);
    }

    public Optional<User> getByUserName(String userName) {
        return userRepository.findOneByUserName(userName);
    }

    public List<User> getListUserOnly() {
        return userRepository.findAll();
    }

    public UserRequest getUserById(Integer userId) {

        UserRequest userRequest = new UserRequest();
        User user = userRepository.findById(userId).orElse(null);
        List<Address> addresses = addressRepository.findAllByUserId(user.getId());
        List<AddressRequest> addressRequestList = new ArrayList<>();
        for(int i=0;i<addresses.size();i++){
            AddressRequest addressRequest = new AddressRequest();
            if(user.getId()==addresses.get(i).getUser().getId()){
                addressRequest.setId(addresses.get(i).getId());
                addressRequest.setDescription(addresses.get(i).getDescription());
                addressRequest.setStatus(addresses.get(i).getStatus());
                addressRequestList.add(addressRequest);
            }
        }
        userRequest.setId(user.getId());
        userRequest.setUserName(user.getUserName());
        userRequest.setEmail(user.getEmail());
        userRequest.setPassword(user.getPassword());
        userRequest.setStatus(user.getStatus());
        userRequest.setImageUser(user.getImageUser());
        userRequest.setAuthority(user.getAuthority());
        userRequest.setActivated(user.getActivated());
        userRequest.setCreatedAt(user.getCreatedAt());
        userRequest.setUpdatedAt(user.getUpdatedAt());
        userRequest.setPerson(user.getPerson());

        userRequest.setAddressRequests(addressRequestList);

        return userRequest;
    }

    public User updateImageUser(User user) {
        User user1 = userRepository.findById(user.getId()).get();
        user1.setImageUser(user.getImageUser());
        return userRepository.save(user1);
    }

    public User updatePasswordUser(ResetPasswordDTO resetPasswordDTO) {
        User user1 = userRepository.findById(resetPasswordDTO.getId()).get();

        if(resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getConfirmPassword())){
            System.out.println(resetPasswordDTO.getPassword());
            System.out.println(user1.getPassword());
            boolean val = passwordEncoder.matches(resetPasswordDTO.getPassword(), user1.getPassword());
            if (val) {
                user1.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
                return userRepository.save(user1);
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    public User deleteCustomerById(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        user.setActivated(false);
        return userRepository.save(user);
    }



}
