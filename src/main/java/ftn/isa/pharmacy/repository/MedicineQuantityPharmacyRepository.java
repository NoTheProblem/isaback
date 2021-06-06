package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.Medicine;
import ftn.isa.pharmacy.model.MedicineQuantityPharmacy;
import ftn.isa.pharmacy.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MedicineQuantityPharmacyRepository extends JpaRepository<MedicineQuantityPharmacy, Long> {

    @Override
    List<MedicineQuantityPharmacy> findAll();

    List<MedicineQuantityPharmacy> findAllByPharmacy(Pharmacy pharmacy);

    Boolean existsByPharmacyAndMedicine(Pharmacy pharmacy, Medicine medicine);

    @Transactional
    @Modifying
    @Query(value = "update medicine_quantity_pharmacy set quantity = quantity + ?3 where pharmacy_id = ?1 and medicine_id = ?2",
            nativeQuery = true)
    void updateQuan(Long pharmacyID, Long medicineID, Long addedQuan );

    Boolean deleteByPharmacyAndMedicine(Pharmacy pharmacy, Medicine medicine);


}
