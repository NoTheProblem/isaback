package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.DermatologistDto;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.DermatologistMapper;
import ftn.isa.pharmacy.model.Dermatologist;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DermatologistMapperImpl  extends AbstractMapper<Dermatologist, DermatologistDto> implements DermatologistMapper {

    @Override
    public DermatologistDto entity2Bean(Dermatologist entity) {
        DermatologistDto bean = new DermatologistDto();
        BeanUtils.copyProperties(entity, bean);

        return bean;
    }

    @Override
    public Dermatologist bean2Entity(DermatologistDto bean) {
        Dermatologist entity = new Dermatologist();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }


}
