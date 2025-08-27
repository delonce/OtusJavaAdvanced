package org.delonce.signIn.service;

public interface CRUDService<T> {
    T create(T obj) throws RuntimeException;

    T read(Integer id);

    T update(T obj);

    void delete(Integer id);
}
