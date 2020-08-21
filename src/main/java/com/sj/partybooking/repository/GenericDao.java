package com.sj.partybooking.repository;

import com.sj.partybooking.domain.AbstractModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericDao<T extends AbstractModel>  extends CrudRepository<T, Long> {
}
