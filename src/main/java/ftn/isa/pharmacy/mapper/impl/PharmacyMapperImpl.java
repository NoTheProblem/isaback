package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.MedicineRegisterDto;
import ftn.isa.pharmacy.model.Medicine;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import ftn.isa.pharmacy.dto.PharmacyDto;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.PharmacyMapper;
import ftn.isa.pharmacy.model.Pharmacy;

@Component
public class PharmacyMapperImpl extends AbstractMapper<Pharmacy, PharmacyDto> implements PharmacyMapper {

	@Override
	public PharmacyDto entity2Bean(Pharmacy entity) {
		PharmacyDto bean = new PharmacyDto();
		BeanUtils.copyProperties(entity, bean);
		return bean;
	}

	@Override
	public Pharmacy bean2Entity(PharmacyDto bean) {
		Pharmacy entity = new Pharmacy();
		BeanUtils.copyProperties(bean, entity);
		return entity;
	}

}
