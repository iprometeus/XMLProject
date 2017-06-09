package com.epam.lab.dao.impl;

import com.epam.lab.dao.ChocolateDAO;
import com.epam.lab.dao.IngredientsDAO;
import com.epam.lab.domain.Chocolate;
import com.epam.lab.domain.Ingredients;
import com.epam.lab.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class IngredientsDAOImpl implements IngredientsDAO {

    private static Logger logger = LogManager.getLogger(IngredientsDAOImpl.class.getName());
    private Connection conn;
    private ChocolateDAO chocolateDAO;

    public IngredientsDAOImpl() {
        chocolateDAO = new ChocolateDAOImpl();
    }

    @Override
    public void add(Ingredients ingredients) {
        try {
            chocolateDAO.add(ingredients.getChocolate());

            conn = DBUtil.getConnection();

            String query = "INSERT INTO ingredients(water, sugar, fruit_sugar, vanillin, chocolate_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, ingredients.getWater());
            preparedStatement.setInt(2, ingredients.getSugar());
            preparedStatement.setInt(3, ingredients.getFruitSugar());
            preparedStatement.setInt(4, ingredients.getVanillin());
            preparedStatement.setInt(5, ingredients.getChocolate().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                ingredients.setId(generatedKeys.getInt(1));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            DBUtil.closeConnection();
        }
    }

    @Override
    public Ingredients getById(int ingredientsId) {
        Ingredients ingredients = new Ingredients();
        try {
            conn = DBUtil.getConnection();
            String query = "SELECT * FROM ingredients WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, ingredientsId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ingredients.setId(ingredientsId);
                ingredients.setWater(resultSet.getInt("water"));
                ingredients.setSugar(resultSet.getInt("sugar"));
                ingredients.setFruitSugar(resultSet.getInt("fruit_sugar"));
                ingredients.setVanillin(resultSet.getInt("vanillin"));

                int chocolateId = resultSet.getInt("chocolate_id");
                Chocolate chocolate = chocolateDAO.getById(chocolateId);
                ingredients.setChocolate(chocolate);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return ingredients;
    }
}
