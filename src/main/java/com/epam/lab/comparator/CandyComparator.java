package com.epam.lab.comparator;

import com.epam.lab.domain.Candy;

import java.util.Comparator;

public class CandyComparator implements Comparator<Candy> {

    @Override
    public int compare(Candy candy1, Candy candy2) {
        String name1 = candy1.getName();
        String name2 = candy2.getName();

        return name1.compareTo(name2);
    }
}
