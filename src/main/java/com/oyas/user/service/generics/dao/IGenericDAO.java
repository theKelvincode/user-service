package com.oyas.user.service.generics.dao;

import java.util.List;

public interface IGenericDAO {

    List<?> getAllObjects(Class<?> clazz);

    <T> void saveObject(T entity);

    <T> T findObjectById(Class<T> tClass, Long id);

    <T> void updateObject(T entity);

    <T> void deleteObject(Class<T> tClass);
}
