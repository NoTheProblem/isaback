package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.SupplierDTO;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.SupplierMapper;
import ftn.isa.pharmacy.model.Supplier;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapperImpl extends AbstractMapper<Supplier, SupplierDTO> implements SupplierMapper {

    @Override
    public SupplierDTO entity2Bean(Supplier entity) {
        SupplierDTO bean = new SupplierDTO();
        BeanUtils.copyProperties(entity, bean);
        return bean;
    }

    @Override
    public Supplier bean2Entity(SupplierDTO bean) {
        Supplier entity = new Supplier();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }
}



