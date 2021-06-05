package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.BidDTO;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.BidMapper;
import ftn.isa.pharmacy.model.Bid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BidMapperImpl extends AbstractMapper<Bid, BidDTO> implements BidMapper {
    @Override
    public BidDTO entity2Bean(Bid entity) {
        BidDTO bean = new BidDTO();
        BeanUtils.copyProperties(entity, bean);
        return bean;
    }

    @Override
    public Bid bean2Entity(BidDTO bean) {
        Bid entity = new Bid();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }
}
