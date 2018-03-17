package com.epam.lab.dao;

import com.epam.lab.domain.Candy;

import java.util.List;

public interface CandyDAO {

    void addAll(List<Candy> candies);

    void add(Candy candy);

    List<Candy> getAll();
}
