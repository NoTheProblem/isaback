package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.AbsenceDTO;
import ftn.isa.pharmacy.mapper.AbsenceMapper;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.model.AbsenceRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AbsenceMapperImpl extends AbstractMapper<AbsenceRequest, AbsenceDTO> implements AbsenceMapper {
    @Override
    public AbsenceDTO entity2Bean(AbsenceRequest entity) {
        AbsenceDTO bean = new AbsenceDTO();
        BeanUtils.copyProperties(entity, bean);
        return  bean;
    }

    @Override
    public AbsenceRequest bean2Entity(AbsenceDTO bean) {
        AbsenceRequest entity = new AbsenceRequest();
        BeanUtils.copyProperties(bean,entity);
        return  entity;
    }

}
