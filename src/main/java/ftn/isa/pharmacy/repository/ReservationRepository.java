package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.Pharmacy;
import ftn.isa.pharmacy.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    List<Reservation> findAllByPharmacyAndPickUpDateBetweenAndPickedUpIsTrue(Pharmacy pharmacy, Date startDate, Date endDate);
}
