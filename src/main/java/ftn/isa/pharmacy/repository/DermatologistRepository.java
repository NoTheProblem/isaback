package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.Dermatologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DermatologistRepository extends JpaRepository<Dermatologist, Long> {


    @Query(value = "SELECT * FROM pharmacy_dermatologist pd WHERE pd.type_of_employee = 'ROLE_PHARMACIST' and ar.status = 'nov'",
            nativeQuery = true)
    List<Dermatologist> getAllCandidates(Long pharmacyID);
}
