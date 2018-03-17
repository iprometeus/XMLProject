package com.epam.lab.dao.impl;

import com.epam.lab.dao.ValueDAO;
import com.epam.lab.domain.Value;
import com.epam.lab.transformer.Transformer;
import com.epam.lab.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ValueDAOImpl implements ValueDAO {

    private static Logger logger = LogManager.getLogger(ValueDAOImpl.class.getName());
    private Connection conn;
    private Transformer<Value> transformer;

    public ValueDAOImpl() {
        transformer = new Transformer<>(Value.class);
    }

    @Override
    public void add(Value value) {
        try {
            conn = DBUtil.getConnection();
            String query = "INSERT INTO value(proteins, carbohydrates, fats) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, value.getProteins());
            preparedStatement.setInt(2, value.getCarbohydrates());
            preparedStatement.setInt(3, value.getFats());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                value.setId(generatedKeys.getInt(1));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            DBUtil.closeConnection();
        }
    }

    @Override
    public Value getById(int valueId) {
        Value value = null;
        try {
            conn = DBUtil.getConnection();
            String query = "SELECT * FROM value WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, valueId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                value = transformer.transformToInstance(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return value;
    }
}
