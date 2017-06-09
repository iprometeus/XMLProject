package com.epam.lab.dao;

import com.epam.lab.domain.Value;

public interface ValueDAO {

    void add(Value value);

    Value getById(int valueId);
}
