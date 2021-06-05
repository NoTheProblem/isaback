package ftn.isa.pharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.isa.pharmacy.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(String name);
}