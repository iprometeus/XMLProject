package com.epam.lab.service.impl;

import com.epam.lab.dao.ProductionDAO;
import com.epam.lab.dao.impl.ProductionDAOImpl;
import com.epam.lab.domain.Production;
import com.epam.lab.service.ProductionService;

public class ProductionServiceImpl implements ProductionService {

    private ProductionDAO productionDAO;

    public ProductionServiceImpl() {
        productionDAO = new ProductionDAOImpl();
    }

    @Override
    public void add(Production production) {
        productionDAO.add(production);
    }

    @Override
    public Production getById(int productionId) {
        return productionDAO.getById(productionId);
    }
}
