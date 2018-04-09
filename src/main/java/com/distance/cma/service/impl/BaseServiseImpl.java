package com.distance.cma.service.impl;

import com.distance.cma.repository.BaseRepository;
import com.distance.cma.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author jerry.li
 * @version 0.1 2017年09月15
 */
public abstract class BaseServiseImpl<T, ID extends Serializable> implements BaseService<T, ID> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EntityManager entityManager;

    /**
     * <p>
     * Description:子类实现该方法<br />
     * </p>
     *
     * @return IBaseRepository<E,PK>
     * @author cissy
     * @version 0.1 2017年4月1日
     */
    protected abstract BaseRepository<T, ID> getBaseRepository();

    @Override
    @Transactional
    public T save(T entity) {
        return getBaseRepository().save(entity);
    }

    @Override
    @Transactional
    public T saveAndFlush(T entity) {
        return getBaseRepository().saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        getBaseRepository().delete(entity);
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        getBaseRepository().deleteById(id);
    }

    @Override
    public T getOne(ID id) {
        return getBaseRepository().getOne(id);
    }

    @Override
    public List<T> findAll() {
        return getBaseRepository().findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return getBaseRepository().findAll(sort);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return getBaseRepository().findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return getBaseRepository().findAll(example, sort);
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return getBaseRepository().findAllById(ids);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getBaseRepository().findAll(pageable);
    }

    @Override
    public Page<T> findAll(Example<T> example, Pageable pageable) {
        return getBaseRepository().findAll(example, pageable);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return getBaseRepository().findAll(spec, pageable);
    }

}
