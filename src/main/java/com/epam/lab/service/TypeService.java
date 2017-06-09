package com.epam.lab.service;

import com.epam.lab.domain.Type;

public interface TypeService {

    void add(Type type);

    Type getById(int typeId);
}
