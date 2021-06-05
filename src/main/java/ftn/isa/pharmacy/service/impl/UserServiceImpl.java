package ftn.isa.pharmacy.service.impl;

import java.util.List;
import java.util.Optional;

import ftn.isa.pharmacy.dto.UserDTO;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ftn.isa.pharmacy.service.AuthorityService;
import ftn.isa.pharmacy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Autowired
    private PharmacyAdminRepository pharmacyAdminRepository;

    @Autowired
    private SysAdminRepository sysAdminRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authService;

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username);
        return u;
    }

    public User findOne(Long id) throws AccessDeniedException {
        User u = userRepository.findById(id).orElseGet(null);
        return u;
    }

    public List<User> findAll() throws AccessDeniedException {
        List<User> result = userRepository.findAll();
        return result;
    }


    @Override
    public User saveEmployee(UserDTO userDTO) {
        if(userDTO.getTip().equals("ROLE_SYSADMIN")){
            return this.saveSYSadmin(userDTO);
        }
        if(userDTO.getTip().equals("ROLE_ADMIN")){
            return this.saveAdmin(userDTO);
        }
        if(userDTO.getTip().equals("ROLE_DERMATOLOGIST")){
            return this.saveDermatologist(userDTO);
        }
        if(userDTO.getTip().equals("ROLE_PHARMACIST")){
            return this.savePharmacist(userDTO);
        }
        if(userDTO.getTip().equals("ROLE_SUPPLIER")){
            return this.saveSupplier(userDTO);
        }
        return null;
    }

    @Override
    public User updateAnyUser(UserDTO userDTO) {
        Optional<User> u = userRepository.findById(userDTO.getId());
        if(u.isPresent()){
            User user = u.get();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setAddress(userDTO.getAddress());
            user.setBirthDate(userDTO.getBirthDate());
            user.setCity(userDTO.getCity());
            user.setCountry(userDTO.getCountry());
            user.setEmail(userDTO.getEmail());
            user.setUsername(userDTO.getUsername());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            userRepository.save(user);
            return user;
        }
        return  null;

    }


    @Override
    public User saveUser(UserDTO userDTO) {
        Patient u = new Patient();
        u.setUsername(userDTO.getUsername());
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u.setFirstName(userDTO.getFirstName());
        u.setLastName(userDTO.getLastName());
        u.setAddress(userDTO.getAddress());
        u.setBirthDate(userDTO.getBirthDate());
        u.setCity(userDTO.getCity());
        u.setCountry(userDTO.getCountry());
        u.setEmail(userDTO.getEmail());
        u.setUsername(userDTO.getUsername());
        u.setPhoneNumber(userDTO.getPhoneNumber());
        u.setEnabled(true);
        u.setTip((userDTO.getTip()));
        List<Authority> auth = authService.findByName(u.getTip());
        u.setAuthorities(auth);
        u = this.patientRepository.save(u);
        return u;
    }


    @Override
    public User saveSYSadmin(UserDTO userDTO) {
        SysAdmin u = new SysAdmin();
        u.setUsername(userDTO.getUsername());
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u.setFirstName(userDTO.getFirstName());
        u.setLastName(userDTO.getLastName());
        u.setAddress(userDTO.getAddress());
        u.setBirthDate(userDTO.getBirthDate());
        u.setCity(userDTO.getCity());
        u.setCountry(userDTO.getCountry());
        u.setEmail(userDTO.getEmail());
        u.setUsername(userDTO.getUsername());
        u.setPhoneNumber(userDTO.getPhoneNumber());
        u.setEnabled(true);
        u.setTip((userDTO.getTip()));
        List<Authority> auth = authService.findByName(u.getTip());
        u.setAuthorities(auth);
            u = this.sysAdminRepository.save(u);
        return u;
    }

    @Override
    public User saveDermatologist(UserDTO userDTO) {
        Dermatologist u = new Dermatologist();
        u.setUsername(userDTO.getUsername());
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u.setFirstName(userDTO.getFirstName());
        u.setLastName(userDTO.getLastName());
        u.setAddress(userDTO.getAddress());
        u.setBirthDate(userDTO.getBirthDate());
        u.setCity(userDTO.getCity());
        u.setCountry(userDTO.getCountry());
        u.setEmail(userDTO.getEmail());
        u.setUsername(userDTO.getUsername());
        u.setPhoneNumber(userDTO.getPhoneNumber());
        u.setEnabled(true);
        u.setTip((userDTO.getTip()));
        List<Authority> auth = authService.findByName(u.getTip());
        u.setAuthorities(auth);
        u = this.dermatologistRepository.save(u);
        return u;
    }

    @Override
    public User savePharmacist(UserDTO userDTO) {
        Pharmacist u = new Pharmacist();
        u.setUsername(userDTO.getUsername());
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u.setFirstName(userDTO.getFirstName());
        u.setLastName(userDTO.getLastName());
        u.setAddress(userDTO.getAddress());
        u.setBirthDate(userDTO.getBirthDate());
        u.setCity(userDTO.getCity());
        u.setCountry(userDTO.getCountry());
        u.setEmail(userDTO.getEmail());
        u.setUsername(userDTO.getUsername());
        u.setPhoneNumber(userDTO.getPhoneNumber());
        u.setEnabled(true);
        u.setTip((userDTO.getTip()));
        List<Authority> auth = authService.findByName(u.getTip());
        u.setAuthorities(auth);
        u = this.pharmacistRepository.save(u);
        return u;
    }

    @Override
    public User saveAdmin(UserDTO userDTO) {
        PharmacyAdmin u = new PharmacyAdmin();
        u.setUsername(userDTO.getUsername());
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u.setFirstName(userDTO.getFirstName());
        u.setLastName(userDTO.getLastName());
        u.setAddress(userDTO.getAddress());
        u.setBirthDate(userDTO.getBirthDate());
        u.setCity(userDTO.getCity());
        u.setCountry(userDTO.getCountry());
        u.setEmail(userDTO.getEmail());
        u.setUsername(userDTO.getUsername());
        u.setPhoneNumber(userDTO.getPhoneNumber());
        u.setEnabled(true);
        u.setTip((userDTO.getTip()));
        List<Authority> auth = authService.findByName(u.getTip());
        u.setAuthorities(auth);
        u = this.pharmacyAdminRepository.save(u);
        return u;
    }

    @Override
    public User saveSupplier(UserDTO userDTO) {
        Supplier u = new Supplier();
        u.setUsername(userDTO.getUsername());
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u.setFirstName(userDTO.getFirstName());
        u.setLastName(userDTO.getLastName());
        u.setAddress(userDTO.getAddress());
        u.setBirthDate(userDTO.getBirthDate());
        u.setCity(userDTO.getCity());
        u.setCountry(userDTO.getCountry());
        u.setEmail(userDTO.getEmail());
        u.setUsername(userDTO.getUsername());
        u.setPhoneNumber(userDTO.getPhoneNumber());
        u.setEnabled(true);
        u.setTip((userDTO.getTip()));
        List<Authority> auth = authService.findByName(u.getTip());
        u.setAuthorities(auth);
        u = this.supplierRepository.save(u);
        return u;
    }



    @Override
    public List<User> findAllbyType(String type) {
        return this.userRepository.findAllByTip(type);
    }

}

