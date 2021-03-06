package ftn.isa.pharmacy.repository;


import ftn.isa.pharmacy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername( String username );

    List<User> findAllByTip(String tip);

}
