package com.epam.lab.wrapper;

import com.epam.lab.domain.Candy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Sweets {

    @XmlElement(name = "candy")
    private List<Candy> candies;

    public Sweets() {
    }

    public Sweets(List<Candy> candies) {
        this.candies = candies;
    }

    public List<Candy> getCandies() {
        return candies;
    }

    public void setCandies(List<Candy> candies) {
        this.candies = candies;
    }

    @Override
    public String toString() {
        return "Sweets{" +
                "candies=" + candies +
                '}';
    }
}
