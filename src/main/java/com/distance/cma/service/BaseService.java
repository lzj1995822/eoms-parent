package com.distance.cma.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, ID extends Serializable> {

    T save(T entity);

    T saveAndFlush(T entity);

    void delete(T entity);

    void deleteById(ID id);

    T getOne(ID id);

    List<T> findAll();

    List<T> findAll(Sort sort);

    List<T> findAllById(Iterable<ID> ids);

    <S extends T> List<S> findAll(Example<S> example);

    <S extends T> List<S> findAll(Example<S> example, Sort sort);

    Page<T> findAll(Pageable pageable);

    Page<T> findAll(Example<T> example, Pageable pageable);

    Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);

}
