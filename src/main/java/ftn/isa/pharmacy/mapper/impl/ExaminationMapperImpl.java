package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.ExaminationDto;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.model.Examination;
import ftn.isa.pharmacy.mapper.ExaminationMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ExaminationMapperImpl  extends AbstractMapper<Examination, ExaminationDto> implements ExaminationMapper{

    @Override
    public ExaminationDto entity2Bean(Examination entity) {
        ExaminationDto bean = new ExaminationDto();
        BeanUtils.copyProperties(entity, bean);
        bean.setDermatologistId(entity.getDermatologist().getId());
        bean.setDermatologistEvaluationGrade(entity.getDermatologist().getEvaluationGrade());
        bean.setDermatologistLastname(entity.getDermatologist().getLastName());
        bean.setDermatologistName(entity.getDermatologist().getFirstName());
        bean.setDate(entity.getDate());
        return bean;
    }

    @Override
    public Examination bean2Entity(ExaminationDto bean) {
        Examination entity = new Examination();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }


}
