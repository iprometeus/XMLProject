package com.epam.lab.service.impl;

import com.epam.lab.dao.TypeDAO;
import com.epam.lab.dao.impl.TypeDAOImpl;
import com.epam.lab.domain.Type;
import com.epam.lab.service.TypeService;

public class TypeServiceImpl implements TypeService {

    private TypeDAO typeDAO;

    public TypeServiceImpl() {
        typeDAO = new TypeDAOImpl();
    }

    @Override
    public void add(Type type) {
        typeDAO.add(type);
    }

    @Override
    public Type getById(int typeId) {
        return typeDAO.getById(typeId);
    }
}
