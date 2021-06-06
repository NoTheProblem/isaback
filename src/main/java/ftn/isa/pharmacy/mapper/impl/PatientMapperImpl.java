package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.PatientDTO;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.PatientMapper;
import ftn.isa.pharmacy.model.Patient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class PatientMapperImpl  extends AbstractMapper<Patient, PatientDTO> implements PatientMapper {

    @Override
    public PatientDTO entity2Bean(Patient entity) {
        PatientDTO bean = new PatientDTO();
        BeanUtils.copyProperties(entity, bean);
        return bean;
    }

    @Override
    public Patient bean2Entity(PatientDTO bean) {
        Patient entity = new Patient();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }
}
