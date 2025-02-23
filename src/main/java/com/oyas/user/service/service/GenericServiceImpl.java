package com.oyas.user.service.service;

import com.oyas.user.service.generic.dao.GenericDAOImpl;
import com.oyas.user.service.generic.dao.IGenericDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public <T> T findObjectById(Class<T> obj, Long id) {
        return genericDAO.getObject(obj, id);
    }

    @Override
    public <T> void updateObject(T obj) {
        genericDAO.updateObject(obj);
    }

    @Override
    public <T> void deletedObject(Class<T> obj) {
        genericDAO.deleteObject(obj);
    }


}