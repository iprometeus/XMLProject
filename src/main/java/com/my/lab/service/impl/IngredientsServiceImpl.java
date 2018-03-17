package com.epam.lab.service.impl;

import com.epam.lab.dao.IngredientsDAO;
import com.epam.lab.dao.impl.IngredientsDAOImpl;
import com.epam.lab.domain.Ingredients;
import com.epam.lab.service.IngredientsService;

public class IngredientsServiceImpl implements IngredientsService {

    private IngredientsDAO ingredientsDAO;

    public IngredientsServiceImpl() {
        ingredientsDAO = new IngredientsDAOImpl();
    }

    @Override
    public void add(Ingredients ingredients) {
        ingredientsDAO.add(ingredients);
    }

    @Override
    public Ingredients getById(int ingredientsId) {
        return ingredientsDAO.getById(ingredientsId);
    }
}
