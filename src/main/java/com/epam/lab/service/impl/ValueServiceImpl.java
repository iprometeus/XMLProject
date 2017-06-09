package com.epam.lab.service.impl;

import com.epam.lab.dao.ValueDAO;
import com.epam.lab.dao.impl.ValueDAOImpl;
import com.epam.lab.domain.Value;
import com.epam.lab.service.ValueService;

public class ValueServiceImpl implements ValueService {

    private ValueDAO valueDAO;

    public ValueServiceImpl() {
        valueDAO = new ValueDAOImpl();
    }

    @Override
    public void add(Value value) {
        valueDAO.add(value);
    }

    @Override
    public Value getById(int valueId) {
        return valueDAO.getById(valueId);
    }
}
