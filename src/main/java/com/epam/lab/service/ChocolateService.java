package com.epam.lab.service;

import com.epam.lab.domain.Chocolate;

public interface ChocolateService {

    void add(Chocolate chocolate);

    Chocolate getById(int chocolateId);
}
