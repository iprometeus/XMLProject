package com.epam.lab.dao;

import com.epam.lab.domain.Type;

public interface TypeDAO {

    void add(Type type);

    Type getById(int typeId);
}
