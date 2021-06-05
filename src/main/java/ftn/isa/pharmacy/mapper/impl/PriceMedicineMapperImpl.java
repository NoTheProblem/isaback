package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.PriceMediceDTO;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.PriceMediceMapper;
import ftn.isa.pharmacy.model.PriceMediceList;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PriceMedicineMapperImpl extends AbstractMapper<PriceMediceList, PriceMediceDTO> implements PriceMediceMapper {

    @Override
    public PriceMediceDTO entity2Bean(PriceMediceList entity) {
        PriceMediceDTO bean = new PriceMediceDTO();
        BeanUtils.copyProperties(entity, bean);
        return  bean;
    }

    @Override
    public PriceMediceList bean2Entity(PriceMediceDTO bean) {
        PriceMediceList entity = new PriceMediceList();
        BeanUtils.copyProperties(bean,entity);
        return  entity;
    }
}
