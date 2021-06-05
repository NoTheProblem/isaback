package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.Pharmacy;
import ftn.isa.pharmacy.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    List<Promotion> findAllByEndDateAfter(Date endDate);
    List<Promotion> findAllByPharmacy(Pharmacy pharmacy);
    List<Promotion> findAllByPharmacyAndEndDateAfter(Pharmacy pharmacy, Date endDate);

}
