package ftn.isa.pharmacy.mapper;

import java.util.Collection;

public interface Mapper<TEntity, TBean> {

	TBean entity2Bean(TEntity entity);
	
	TEntity bean2Entity(TBean bean);
	
	Collection<TBean> entity2Bean(Collection<TEntity> entities);
	
	Collection<TEntity> bean2Entity(Collection<TBean> beans);
	
}
