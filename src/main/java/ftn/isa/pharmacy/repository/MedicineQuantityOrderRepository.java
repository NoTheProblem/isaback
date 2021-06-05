package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.MedicineQuantityOrder;
import ftn.isa.pharmacy.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineQuantityOrderRepository extends JpaRepository<MedicineQuantityOrder, Long> {

    List<MedicineQuantityOrder> findAllByPurchaseOrder(PurchaseOrder purchaseOrder);

}
