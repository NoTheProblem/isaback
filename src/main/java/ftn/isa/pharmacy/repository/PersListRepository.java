package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.PersList;
import ftn.isa.pharmacy.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PersListRepository extends JpaRepository<PersList, Long> {

    List<PersList> findAllByPharmacyAndDateOfPurchaseBetween(Pharmacy pharmacy, Date startDate, Date endDate);
}
