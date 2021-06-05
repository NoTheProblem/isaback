package ftn.isa.pharmacy.mapper;

import ftn.isa.pharmacy.dto.MedicineQuantityDto;
import ftn.isa.pharmacy.model.MedicineQuantityOrder;
import ftn.isa.pharmacy.model.PurchaseOrder;

import java.util.Set;

public interface QuantityMapper{

    Set<MedicineQuantityOrder> quantityOrderDTOtoQuantityOrder(MedicineQuantityDto medQuan, PurchaseOrder purchaseOrder);
}


