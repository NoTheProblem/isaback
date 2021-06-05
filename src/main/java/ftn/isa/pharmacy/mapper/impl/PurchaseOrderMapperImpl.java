package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.dto.PurchaseOrderDTO;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.MedicineMapper;
import ftn.isa.pharmacy.mapper.PurchaseOrderMapper;
import ftn.isa.pharmacy.model.Medicine;
import ftn.isa.pharmacy.model.PurchaseOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PurchaseOrderMapperImpl extends AbstractMapper<PurchaseOrder, PurchaseOrderDTO> implements PurchaseOrderMapper {

    @Override
    public PurchaseOrderDTO entity2Bean(PurchaseOrder entity) {
        PurchaseOrderDTO bean = new PurchaseOrderDTO();
        BeanUtils.copyProperties(entity, bean);
        return bean;
    }

    @Override
    public PurchaseOrder bean2Entity(PurchaseOrderDTO bean) {
        PurchaseOrder entity = new PurchaseOrder();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }

}

