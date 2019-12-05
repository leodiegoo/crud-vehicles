package br.com.crud.vehicles.repository;

import java.util.List;

public interface JOOQRepository<T, PK> {
    T add(T tEntry);

    T delete(PK id);

    List<T> findAll();

    T findById(PK id);

    T update(T tEntry);
}
