package com.epam.lab.domain;

import com.epam.lab.annotation.Column;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Ingredients {

    @Column(name = "id")
    private int id;
    @Column(name = "water")
    private int water;
    @Column(name = "sugar")
    private int sugar;
    @Column(name = "fruitSugar")
    private int fruitSugar;
    @Column(name = "vanillin")
    private int vanillin;
    private Chocolate chocolate;

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public int getWater() {
        return water;
    }

    @XmlElement
    public void setWater(int water) {
        this.water = water;
    }

    public int getSugar() {
        return sugar;
    }

    @XmlElement
    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getFruitSugar() {
        return fruitSugar;
    }

    @XmlElement
    public void setFruitSugar(int fruitSugar) {
        this.fruitSugar = fruitSugar;
    }

    public Chocolate getChocolate() {
        return chocolate;
    }

    @XmlElement
    public void setChocolate(Chocolate chocolate) {
        this.chocolate = chocolate;
    }

    public int getVanillin() {
        return vanillin;
    }

    @XmlElement
    public void setVanillin(int vanillin) {
        this.vanillin = vanillin;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "id=" + id +
                ", water=" + water +
                ", sugar=" + sugar +
                ", fruitSugar=" + fruitSugar +
                ", chocolate=" + chocolate +
                ", vanillin=" + vanillin +
                '}';
    }
}
