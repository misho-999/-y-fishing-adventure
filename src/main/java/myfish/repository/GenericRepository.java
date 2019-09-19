package myfish.repository;

import java.util.List;

public interface GenericRepository<E, P> {

    E save(E entity);

    List<E> findAll();

    E findById(P id);

    E findByName(P name);

    long size();
}
