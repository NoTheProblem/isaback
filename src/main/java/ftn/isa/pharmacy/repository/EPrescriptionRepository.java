package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.EPrescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EPrescriptionRepository extends JpaRepository<EPrescription, Long> {
}
