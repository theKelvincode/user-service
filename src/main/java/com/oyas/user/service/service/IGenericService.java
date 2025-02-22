package com.oyas.user.service.service;

import java.util.List;

public interface IGenericService {

    List<?> fetchAllObjects(Class<?> clazz);

    <T> T saveObject(T obj);

    <T> T findObjectById(Class<T> obj, Long id);

    <T> void updateObject(T obj);

    <T> void deletedObject(Class<T> obj);
}