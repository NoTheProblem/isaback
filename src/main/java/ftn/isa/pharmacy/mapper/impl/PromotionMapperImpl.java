package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.PromotionDTO;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.PromotionMapper;
import ftn.isa.pharmacy.model.Promotion;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PromotionMapperImpl extends AbstractMapper<Promotion, PromotionDTO> implements PromotionMapper {

    @Override
    public PromotionDTO entity2Bean(Promotion promotion) {
        PromotionDTO bean = new PromotionDTO();
        BeanUtils.copyProperties(promotion,bean);
        return  bean;
    }

    @Override
    public Promotion bean2Entity(PromotionDTO bean) {
        Promotion entity = new Promotion();
        BeanUtils.copyProperties(bean, entity);
        return  entity;
    }
}
