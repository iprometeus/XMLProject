package com.epam.lab.domain;

import com.epam.lab.annotation.Column;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Candy {

    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "energy")
    private int energy;
    private Type type;
    private Ingredients ingredients;
    private Value value;
    private Production production;

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    @XmlElement
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public Type getType() {
        return type;
    }

    @XmlElement
    public void setType(Type type) {
        this.type = type;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    @XmlElement
    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public Value getValue() {
        return value;
    }

    @XmlElement
    public void setValue(Value value) {
        this.value = value;
    }

    public Production getProduction() {
        return production;
    }

    @XmlElement
    public void setProduction(Production production) {
        this.production = production;
    }

    @Override
    public String toString() {
        return "Candy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", energy=" + energy +
                ", type=" + type +
                ", ingredients=" + ingredients +
                ", value=" + value +
                ", production=" + production +
                '}';
    }
}
