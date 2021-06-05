package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
