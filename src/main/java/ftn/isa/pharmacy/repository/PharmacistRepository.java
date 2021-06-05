package ftn.isa.pharmacy.repository;


import ftn.isa.pharmacy.model.Pharmacist;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
    List<Pharmacist> findAllByPharmacyIsNull();
}
