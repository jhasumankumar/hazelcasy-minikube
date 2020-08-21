package com.sj.partybooking.repository;


import com.sj.partybooking.domain.AbstractModel;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public class GenericDaoImpl<T extends AbstractModel> implements GenericDao<T> {

    protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(GenericDaoImpl.class);

    @PersistenceContext
    protected EntityManager em;

    private Class<T> type;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        //noinspection unchecked
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public List<T> findAll() {
        return Collections.emptyList();
    }

    @Override
    public Iterable<T> findAllById(Iterable<Long> iterable) {
        return null;
    }


    @Override
    public void delete(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends T> iterable) {

    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends T> S save(S s) {
        em.persist(s);
        return s;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> iterable) {
        List<S> result = new ArrayList<>();
        for (S entity : iterable) {
            em.persist(entity);
            result.add(entity);
        }
        return result;
    }

    @Override
    public Optional<T> findById(Long aLong) {
        return Optional.of(em.find(type, aLong));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        Optional<T> t = findById(aLong);
        if (t.isPresent()) {
            em.remove(t);
        }
    }

}
