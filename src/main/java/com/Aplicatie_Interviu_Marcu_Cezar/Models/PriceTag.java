package com.Aplicatie_Interviu_Marcu_Cezar.Models;

import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@Data
public class PriceTag {
    private final String currency = "USD";
    private Double value;

    public PriceTag() {
    }

    @XmlAttribute
    public String getCurrency() {
        return currency;
    }
    @XmlValue
    public Double getValue() {
        return value;
    }
    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PriceTag{" +
                "currency='" + currency + '\'' +
                ", value=" + value +
                '}';
    }
}
