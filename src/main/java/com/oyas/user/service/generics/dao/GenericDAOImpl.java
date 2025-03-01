package com.oyas.user.service.generics.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GenericDAOImpl implements IGenericDAO {

    private final EntityManager entityManager;

    @Autowired
    public GenericDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public <T> void saveObject(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public List<?> getAllObjects(Class<?> clazz) {
        String qlString = "From " + clazz.getName();
        TypedQuery<?> objTypedQuery = entityManager.createQuery(qlString, clazz);
        return objTypedQuery.getResultList();
    }

    public <T> Optional<T> findObjectById(Class<T> tClass, Long id) {
        return Optional.ofNullable(entityManager.find(tClass, id));
    }


    @Transactional
    public <T> void updateObject(T entity) {
        entityManager.merge(entity);
    }

    public <T> void deleteObject(T entity) {
        entityManager.remove(entity);
    }
}