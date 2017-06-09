package com.epam.lab.dao;

import com.epam.lab.domain.Ingredients;

public interface IngredientsDAO {

    void add(Ingredients ingredients);

    Ingredients getById(int ingredientsId);
}
