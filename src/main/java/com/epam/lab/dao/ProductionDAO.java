package com.epam.lab.dao;

import com.epam.lab.domain.Production;

public interface ProductionDAO {

    void add(Production production);

    Production getById(int productionId);
}
