package taller.grado.proyectofinalbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import taller.grado.proyectofinalbackend.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {


    @Query("SELECT a.id, a.description, a.status FROM Address a, User u " +
            "WHERE a.user.id = u.id AND a.user.id = :userId")
    List<Object[]> getAddressesUsers(Integer userId);

    List<Object[]> findAddressesByUserId(Integer userId);

    List<Address> findAllByUserId(Integer userId);

    //List<Address> findAllByUserId(Integer userId)
}
