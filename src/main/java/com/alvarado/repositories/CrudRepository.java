package com.alvarado.repositories;


// A generic type is a class or interface that parameterizes over types.
// We use angle brackets to specify the parameter type.

import java.util.List;

public interface CrudRepository <T>{

    //CREATE
    T add(T t);

    //READ
    T getById(Integer id);
    T getByIDS(Integer id, Integer otherId);
    List<T> getAll();
    List<T> getAllById(Integer id);
    List<T> getAllByIDS(Integer id, Integer otherId);


    //UPDATE
    void update(T t);

    //DELETE
    void delete(T t);
}
