package ftn.isa.pharmacy.mapper;

import ftn.isa.pharmacy.dto.PharmacyDto;
import ftn.isa.pharmacy.model.Pharmacy;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractMapper<TEntity, TBean> implements Mapper<TEntity, TBean> {

	@Override
	public Collection<TBean> entity2Bean(Collection<TEntity> entities) {
		return entities
				.stream()
				.map(entity -> entity2Bean(entity))
				.collect(Collectors.toList());
	}

	@Override
	public Collection<TEntity> bean2Entity(Collection<TBean> beans) {
		return beans
				.stream()
				.map(bean -> bean2Entity(bean))
				.collect(Collectors.toList());
	}

}
