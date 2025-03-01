package com.oyas.user.service.generics.service;

import java.util.List;
import java.util.Optional;

public interface IGenericService {

    List<?> fetchAllObjects(Class<?> clazz);

    <T> T saveObject(T obj);

    <T> Optional<T> findObjectById(Class<T> obj, Long id);

    <T> void updateObject(T obj);

    <T> void deleteObject(T obj);
}