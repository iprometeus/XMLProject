package com.epam.lab.service;

import com.epam.lab.domain.Production;

public interface ProductionService {

    void add(Production production);

    Production getById(int productionId);
}
