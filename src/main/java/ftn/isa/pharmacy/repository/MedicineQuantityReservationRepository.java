package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.MedicineQuantityReservation;
import ftn.isa.pharmacy.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineQuantityReservationRepository extends JpaRepository<MedicineQuantityReservation, Long> {

    List<MedicineQuantityReservation> findAllByReservation(Reservation reservation);
}
