package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.WorkingHoursPharmacistDTO;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.WorkingHoursPharmacistMapper;
import ftn.isa.pharmacy.model.WorkingHoursPharmacist;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class WorkingHoursPharmacistMapperImpl extends AbstractMapper<WorkingHoursPharmacist, WorkingHoursPharmacistDTO> implements WorkingHoursPharmacistMapper {

    @Override
    public WorkingHoursPharmacistDTO entity2Bean(WorkingHoursPharmacist entity) {
        WorkingHoursPharmacistDTO bean = new WorkingHoursPharmacistDTO();
        BeanUtils.copyProperties(entity, bean);
        return bean;
    }

    @Override
    public WorkingHoursPharmacist bean2Entity(WorkingHoursPharmacistDTO bean) {
        WorkingHoursPharmacist entity = new WorkingHoursPharmacist();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }
}
