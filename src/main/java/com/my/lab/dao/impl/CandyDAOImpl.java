package com.epam.lab.dao.impl;

import com.epam.lab.dao.*;
import com.epam.lab.domain.*;
import com.epam.lab.transformer.Transformer;
import com.epam.lab.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandyDAOImpl implements CandyDAO {

    private static Logger logger = LogManager.getLogger(CandyDAOImpl.class.getName());

    private Connection conn;
    private Transformer<Candy> transformer;
    private TypeDAO typeDAO;
    private IngredientsDAO ingredientsDAO;
    private ValueDAO valueDAO;
    private ProductionDAO productionDAO;

    public CandyDAOImpl() {
        transformer = new Transformer<>(Candy.class);
        typeDAO = new TypeDAOImpl();
        ingredientsDAO = new IngredientsDAOImpl();
        valueDAO = new ValueDAOImpl();
        productionDAO = new ProductionDAOImpl();
    }

    @Override
    public void addAll(List<Candy> candies) {
        try {
            addNestedObjects(candies);

            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            String query = "INSERT INTO candy(name, energy, type_id, ingredients_id, value_id, production_id) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);


            for (Candy candy : candies) {
                preparedStatement.setString(1, candy.getName());
                preparedStatement.setInt(2, candy.getEnergy());
                preparedStatement.setInt(3, candy.getType().getId());
                preparedStatement.setInt(4, candy.getIngredients().getId());
                preparedStatement.setInt(5, candy.getValue().getId());
                preparedStatement.setInt(6, candy.getProduction().getId());
                preparedStatement.addBatch();
            }
            int[] numUpdates = preparedStatement.executeBatch();
            for (int i = 0; i < numUpdates.length; i++) {
                if (numUpdates[i] == -2)
                    System.out.println("Execution " + i +
                            ": unknown number of rows updated");
                else
                    System.out.println("Execution " + i +
                            "successful: " + numUpdates[i] + " rows updated");
            }
            preparedStatement.close();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                logger.error(e1.getMessage());
            }
            logger.error(e.getMessage());
        } finally {
            DBUtil.closeConnection();
        }
    }

    @Override
    public void add(Candy candy) {
        try {
            addNestedObjects(candy);

            conn = DBUtil.getConnection();
            String query = "INSERT INTO candy(name, energy, type_id, ingredients_id, value_id, production_id) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, candy.getName());
            preparedStatement.setInt(2, candy.getEnergy());
            preparedStatement.setInt(3, candy.getType().getId());
            preparedStatement.setInt(4, candy.getIngredients().getId());
            preparedStatement.setInt(5, candy.getValue().getId());
            preparedStatement.setInt(6, candy.getProduction().getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            DBUtil.closeConnection();
        }

    }

    @Override
    public List<Candy> getAll() {
        List<Candy> candies = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM candy");
            while (resultSet.next()) {
                Candy candy = transformer.transformToInstance(resultSet);
                int typeId = resultSet.getInt("type_id");
                int ingredientsId = resultSet.getInt("ingredients_id");
                int valueId = resultSet.getInt("value_id");
                int productionId = resultSet.getInt("production_id");
                Type type = typeDAO.getById(typeId);
                Ingredients ingredients = ingredientsDAO.getById(ingredientsId);
                Value value = valueDAO.getById(valueId);
                Production production = productionDAO.getById(productionId);
                candy.setType(type);
                candy.setIngredients(ingredients);
                candy.setValue(value);
                candy.setProduction(production);
                candies.add(candy);
            }
            resultSet.close();
            statement.close();
            conn.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            try {
                if (!conn.isClosed()) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                logger.error(e1.getMessage());
            }
        } finally {
            DBUtil.closeConnection();
        }
        return candies;

    }

    private void addNestedObjects(Candy candy) {
        typeDAO.add(candy.getType());
        ingredientsDAO.add(candy.getIngredients());
        valueDAO.add(candy.getValue());
        productionDAO.add(candy.getProduction());
    }

    private void addNestedObjects(List<Candy> candies) {
        for (Candy candy : candies) {
            typeDAO.add(candy.getType());
            ingredientsDAO.add(candy.getIngredients());
            valueDAO.add(candy.getValue());
            productionDAO.add(candy.getProduction());
        }
    }
}