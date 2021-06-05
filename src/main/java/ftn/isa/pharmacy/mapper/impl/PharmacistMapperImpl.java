package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.DermatologistDto;
import ftn.isa.pharmacy.dto.PharmacistDTO;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.DermatologistMapper;
import ftn.isa.pharmacy.mapper.PharmacistMapper;
import ftn.isa.pharmacy.model.Dermatologist;
import ftn.isa.pharmacy.model.Pharmacist;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PharmacistMapperImpl  extends AbstractMapper<Pharmacist, PharmacistDTO> implements PharmacistMapper {

    @Override
    public PharmacistDTO entity2Bean(Pharmacist entity) {
        PharmacistDTO bean = new PharmacistDTO();
        BeanUtils.copyProperties(entity, bean);
        return bean;
    }

    @Override
    public Pharmacist bean2Entity(PharmacistDTO bean) {
        Pharmacist entity = new Pharmacist();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }
}
