package com.oyas.user.service.generics.service;

import com.oyas.user.service.generics.dao.GenericDAOImpl;
import com.oyas.user.service.generics.dao.IGenericDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenericServiceImpl implements IGenericService {

    private final IGenericDAO genericDAO;

    @Autowired
    public GenericServiceImpl(GenericDAOImpl genericDAO) {
        this.genericDAO = genericDAO;
    }


    @Override
    public List<?> fetchAllObjects(Class<?> clazz) {
        return genericDAO.getAllObjects(clazz);
    }

    @Override
    public <T> T saveObject(T obj) {
        genericDAO.saveObject(obj);
        return obj;
    }

    @Override
    public <T> Optional<T> findObjectById(Class<T> obj, Long id) {
        return genericDAO.findObjectById(obj, id);
    }

    @Override
    public <T> void updateObject(T obj) {
        genericDAO.updateObject(obj);
    }

    @Override
    public <T> void deleteObject(T obj) {
        genericDAO.deleteObject(obj);
    }


}