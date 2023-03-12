package com.Aplicatie_Interviu_Marcu_Cezar.Models;

import com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Formatters.XmlAdapters.PriceFormatter;
import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;

@Data
public class PriceTag {
    //ATTRIBUTES
    private final String currency = "USD";
    private BigDecimal value = BigDecimal.valueOf(0.00);


    //CONSTRUCTOR
    public PriceTag() {}


    //GETTERS AND SETTERS
    @XmlAttribute
    public String getCurrency() {
        return currency;
    }

    @XmlJavaTypeAdapter(PriceFormatter.class)
    @XmlValue
    public BigDecimal getValue() {
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
