package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.Dermatologist;
import ftn.isa.pharmacy.model.Pharmacy;
import ftn.isa.pharmacy.model.WorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Long> {

    WorkingHours getWorkingHoursByPharmacyAndDermatologist(Pharmacy pharmacy, Dermatologist dermatologist);

    List<WorkingHours> findAllByDermatologist(Dermatologist dermatologist);

    Set<WorkingHours> findAllByDermatologistOrderById(Dermatologist dermatologist);
}


