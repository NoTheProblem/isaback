package ftn.isa.pharmacy.service;

import java.util.List;

import ftn.isa.pharmacy.dto.UserDTO;
import ftn.isa.pharmacy.model.User;

public interface UserService {

    User findOne(Long id);
    User findByUsername(String username);
    List<User> findAll ();
    User saveSupplier(UserDTO userDTO);
    User saveAdmin(UserDTO userDTO);
    User savePharmacist(UserDTO userDTO);
    User saveDermatologist(UserDTO userDTO);
    User saveSYSadmin(UserDTO userDTO);
    User saveUser(UserDTO userDTO);
    User saveEmployee(UserDTO userDTO);
    User updateAnyUser(UserDTO userDTO);
    List<User> findAllbyType (String type);


}

