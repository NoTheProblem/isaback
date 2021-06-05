package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
