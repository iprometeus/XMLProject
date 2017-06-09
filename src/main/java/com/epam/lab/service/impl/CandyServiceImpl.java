package com.epam.lab.service.impl;

import com.epam.lab.dao.CandyDAO;
import com.epam.lab.dao.impl.CandyDAOImpl;
import com.epam.lab.domain.Candy;
import com.epam.lab.service.CandyService;

import java.util.List;

public class CandyServiceImpl implements CandyService {

    private CandyDAO candyDAO;

    public CandyServiceImpl() {
        candyDAO = new CandyDAOImpl();
    }

    @Override
    public void addAll(List<Candy> candies) {
        candyDAO.addAll(candies);
    }

    @Override
    public void add(Candy candy) {
        candyDAO.add(candy);
    }

    @Override
    public List<Candy> getAll() {
        return candyDAO.getAll();
    }
}