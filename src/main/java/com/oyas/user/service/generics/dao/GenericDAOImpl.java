package com.oyas.user.service.generics.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenericDAOImpl implements IGenericDAO {

    private final EntityManager entityManager;

    @Autowired
    public GenericDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<?> getAllObjects(Class<?> clazz) {
        String qlString = "From " + clazz.getName();
        TypedQuery<?> objTypedQuery = entityManager.createQuery(qlString, clazz);
        return objTypedQuery.getResultList();
    }


    @Override
    @Transactional
    public <T> void saveObject(T entity) {
      entityManager.persist(entity);
    }

    public <T> T findObjectById(Class<T> tClass, Long id) {
        return entityManager.find(tClass, id);
    }

    @Transactional
    public <T> void updateObject(T entity) {
        entityManager.merge(entity);
    }

    public <T> void deleteObject(Class<T> tClass) {
        entityManager.remove(tClass);
    }


}