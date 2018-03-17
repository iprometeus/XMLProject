package com.epam.lab.dao.impl;

import com.epam.lab.dao.ChocolateDAO;
import com.epam.lab.domain.Chocolate;
import com.epam.lab.transformer.Transformer;
import com.epam.lab.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ChocolateDAOImpl implements ChocolateDAO {

    private static Logger logger = LogManager.getLogger(ChocolateDAOImpl.class.getName());
    private Connection conn;
    private Transformer<Chocolate> transformer;

    public ChocolateDAOImpl() {
        transformer = new Transformer<>(Chocolate.class);
    }

    @Override
    public void add(Chocolate chocolate) {
        try {
            conn = DBUtil.getConnection();
            String query = "INSERT INTO chocolate(name) VALUES (?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, chocolate.getName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                chocolate.setId(generatedKeys.getInt(1));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            DBUtil.closeConnection();
        }
    }

    @Override
    public Chocolate getById(int chocolateId) {
        Chocolate chocolate = null;
        try {
            conn = DBUtil.getConnection();
            String query = "SELECT * FROM chocolate WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, chocolateId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                chocolate = transformer.transformToInstance(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return chocolate;
    }
}
