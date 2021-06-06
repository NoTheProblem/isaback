package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.MedicineQuantityDto;
import ftn.isa.pharmacy.mapper.QuantityMapper;
import ftn.isa.pharmacy.model.MedicineQuantityOrder;
import ftn.isa.pharmacy.model.PurchaseOrder;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class QuantityMapperImpl implements QuantityMapper {

    @Override
    public Set<MedicineQuantityOrder> quantityOrderDTOtoQuantityOrder(MedicineQuantityDto medQuan, PurchaseOrder purchaseOrder) {
        Set<MedicineQuantityOrder> medQuantity = new HashSet<>();
        List<Long> ids = medQuan.getMedicineIDs();
        List<Long> quans = medQuan.getQuantity();
        for(int i = 0; i < ids.size(); i ++){
            MedicineQuantityOrder orderQuan = new MedicineQuantityOrder();
            orderQuan.setMedicine(ids.get(i));
            orderQuan.setQuantity(quans.get(i));
            orderQuan.setPurchaseOrder(purchaseOrder);
            medQuantity.add(orderQuan);
        }
        return medQuantity;
    }
}
