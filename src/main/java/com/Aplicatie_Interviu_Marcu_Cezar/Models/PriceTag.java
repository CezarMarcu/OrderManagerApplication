package com.Aplicatie_Interviu_Marcu_Cezar.Models;

import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@Data
public class PriceTag {
    //ATTRIBUTES
    private final String currency = "USD";
    private Double value;


    //CONSTRUCTOR
    public PriceTag() {}


    //GETTERS AND SETTERS
    @XmlAttribute
    public String getCurrency() {
        return currency;
    }

    @XmlValue
    public Double getValue() {
        return value;
    }


    //METHODS
    @Override
    public String toString() {
        return "PriceTag{" +
                "currency='" + currency + '\'' +
                ", value=" + value +
                '}';
    }
}
