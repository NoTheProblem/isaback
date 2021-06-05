package ftn.isa.pharmacy.repository;


import ftn.isa.pharmacy.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PatientRepository extends JpaRepository<Patient, Long> {


}
