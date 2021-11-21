package com.kisiel.jobs.rest;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<E, K> {

    Optional<E> find(K id);

    List<E> findAll();

    E create(E entity);

    void delete(K id);

}
