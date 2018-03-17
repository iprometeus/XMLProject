package com.epam.lab.dao;

import com.epam.lab.domain.Chocolate;

public interface ChocolateDAO {

    void add(Chocolate chocolate);

    Chocolate getById(int chocolateId);
}
