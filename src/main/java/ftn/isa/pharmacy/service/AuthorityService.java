package ftn.isa.pharmacy.service;


import java.util.List;

import ftn.isa.pharmacy.model.Authority;

public interface AuthorityService {
    List<Authority> findById(Long id);
    List<Authority> findByName(String name);
}
