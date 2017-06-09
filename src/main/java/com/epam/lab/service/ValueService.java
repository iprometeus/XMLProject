package com.epam.lab.service;

import com.epam.lab.domain.Value;

public interface ValueService {

    void add(Value value);

    Value getById(int valueId);
}
