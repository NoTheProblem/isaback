package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.WorkingHoursDTO;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.WorkingHoursMapper;
import ftn.isa.pharmacy.model.WorkingHours;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class WorkingHoursMapperImpl extends AbstractMapper<WorkingHours, WorkingHoursDTO> implements WorkingHoursMapper {


    @Override
    public WorkingHoursDTO entity2Bean(WorkingHours entity) {
        WorkingHoursDTO bean = new WorkingHoursDTO();
        BeanUtils.copyProperties(entity, bean);
        return bean;
    }

    @Override
    public WorkingHours bean2Entity(WorkingHoursDTO bean) {
        WorkingHours entity = new WorkingHours();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }
}
