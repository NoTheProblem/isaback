package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.mapper.MedicineMapper;
import ftn.isa.pharmacy.model.Medicine;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ftn.isa.pharmacy.mapper.AbstractMapper;

@Component
public class MedicineMapperImpl extends AbstractMapper<Medicine, MedicineDto> implements MedicineMapper {

    @Override
    public MedicineDto entity2Bean(Medicine entity) {
        MedicineDto bean = new MedicineDto();
        BeanUtils.copyProperties(entity, bean);
        return bean;
    }

    @Override
    public Medicine bean2Entity(MedicineDto bean) {
        Medicine entity = new Medicine();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }

}
