package com.epam.lab.service.impl;

import com.epam.lab.dao.ChocolateDAO;
import com.epam.lab.dao.impl.ChocolateDAOImpl;
import com.epam.lab.domain.Chocolate;
import com.epam.lab.service.ChocolateService;

public class ChocolateServiceImpl implements ChocolateService {

    private ChocolateDAO chocolateDAO;

    public ChocolateServiceImpl() {
        chocolateDAO = new ChocolateDAOImpl();
    }

    @Override
    public void add(Chocolate chocolate) {
        chocolateDAO.add(chocolate);
    }

    @Override
    public Chocolate getById(int chocolateId) {
        return chocolateDAO.getById(chocolateId);
    }
}
