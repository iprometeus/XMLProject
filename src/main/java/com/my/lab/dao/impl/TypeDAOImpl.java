package com.epam.lab.dao.impl;

import com.epam.lab.dao.TypeDAO;
import com.epam.lab.domain.Type;
import com.epam.lab.transformer.Transformer;
import com.epam.lab.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class TypeDAOImpl implements TypeDAO {

    private static Logger logger = LogManager.getLogger(TypeDAOImpl.class.getName());
    private Connection conn;
    private Transformer<Type> transformer;

    public TypeDAOImpl() {
        transformer = new Transformer<>(Type.class);
    }

    @Override
    public void add(Type type) {
        try {
            conn = DBUtil.getConnection();
            String query = "INSERT INTO type(name) VALUES (?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, type.getName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                type.setId(generatedKeys.getInt(1));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            DBUtil.closeConnection();
        }
    }

    @Override
    public Type getById(int typeId) {
        Type type = null;
        try {
            conn = DBUtil.getConnection();
            String query = "SELECT * FROM type WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, typeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                type = transformer.transformToInstance(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return type;
    }
}
