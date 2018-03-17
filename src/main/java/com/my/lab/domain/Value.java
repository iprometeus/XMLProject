package com.epam.lab.domain;

import com.epam.lab.annotation.Column;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Value {

    @Column(name = "id")
    private int id;
    @Column(name = "proteins")
    private int proteins;
    @Column(name = "carbohydrates")
    private int carbohydrates;
    @Column(name = "fats")
    private int fats;

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public int getProteins() {
        return proteins;
    }

    @XmlElement
    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    @XmlElement
    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public int getFats() {
        return fats;
    }

    @XmlElement
    public void setFats(int fats) {
        this.fats = fats;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", proteins=" + proteins +
                ", carbohydrates=" + carbohydrates +
                ", fats=" + fats +
                '}';
    }
}
