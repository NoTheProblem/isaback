package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.dto.MedicineRegisterDto;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.MedicineRegisterMapper;
import ftn.isa.pharmacy.model.Medicine;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MedicineRegisterMapperImpl extends AbstractMapper<Medicine, MedicineRegisterDto> implements MedicineRegisterMapper {

    @Override
    public MedicineRegisterDto entity2Bean(Medicine entity) {
        MedicineRegisterDto bean = new MedicineRegisterDto();
        BeanUtils.copyProperties(entity, bean);
        return bean;
    }

    @Override
    public Medicine bean2Entity(MedicineRegisterDto bean) {
        Medicine entity = new Medicine();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }

}

