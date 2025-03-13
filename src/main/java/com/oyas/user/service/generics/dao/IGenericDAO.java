package com.oyas.user.service.generics.dao;

import java.util.List;
import java.util.Optional;

public interface IGenericDAO {

    <T> void saveObject(T entity);

    List<?> getAllObjects(Class<?> tClass);

    <T> Optional<T> findObjectById(Class<T> tClass, Long id);

    // TODO; WRITE A GLOBAL METHOD TO FIND OBJECT BY FIELD & CLASS
//    <T> T findObjectByName(Class<T> tClass, String name);

    <T> void updateObject(T entity);

    <T> void deleteObject(T obj);
}
