package com.epam.lab.transformer;

import com.epam.lab.annotation.Column;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transformer<T> {

    private Class<T> clazz;

    public Transformer(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T transformToInstance(ResultSet rs) throws IllegalAccessException, InstantiationException, SQLException {
        T element = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                String columnStr = column.name();
                field.set(element, rs.getObject(columnStr));
            }
        }
        return element;
    }
}
