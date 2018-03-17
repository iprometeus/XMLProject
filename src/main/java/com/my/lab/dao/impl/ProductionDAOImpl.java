package com.epam.lab.dao.impl;

import com.epam.lab.dao.ProductionDAO;
import com.epam.lab.domain.Production;
import com.epam.lab.transformer.Transformer;
import com.epam.lab.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ProductionDAOImpl implements ProductionDAO {

    private static Logger logger = LogManager.getLogger(ProductionDAOImpl.class.getName());
    private Connection conn;
    private Transformer<Production> transformer;

    public ProductionDAOImpl() {
        transformer = new Transformer<>(Production.class);
    }

    @Override
    public void add(Production production) {
        try {
            conn = DBUtil.getConnection();

            String query = "INSERT INTO production(name) VALUES (?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, production.getName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                production.setId(generatedKeys.getInt(1));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            DBUtil.closeConnection();
        }
    }

    @Override
    public Production getById(int productionId) {
        Production production = null;
        try {
            conn = DBUtil.getConnection();
            String query = "SELECT * FROM production WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, productionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                production = transformer.transformToInstance(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return production;
    }
}
