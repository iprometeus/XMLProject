package com.epam.lab.service;

import com.epam.lab.domain.Ingredients;

public interface IngredientsService {

    void add(Ingredients ingredients);

    Ingredients getById(int ingredientsId);
}
