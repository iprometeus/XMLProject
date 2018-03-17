package com.epam.lab.service;

import com.epam.lab.domain.Candy;

import java.util.List;

public interface CandyService {

    void addAll(List<Candy> candies);

    void add(Candy candy);

    List<Candy> getAll();
}
