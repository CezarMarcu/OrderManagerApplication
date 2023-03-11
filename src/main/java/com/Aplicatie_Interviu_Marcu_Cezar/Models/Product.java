package com.Aplicatie_Interviu_Marcu_Cezar.Models;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;
import java.util.Random;


@Data
@AllArgsConstructor
@XmlRootElement(name = "product")
public class Product {

    //ATTRIBUTES
    @NotEmpty(message = "Description cannot be null.")
    private String description;
    private Date orderDate;
    private String gtin;
    private Double value;
    private PriceTag priceTag = new PriceTag();
    @NotEmpty(message = "Supplier cannot be null.")
    private String supplier;


    //CONSTRUCTOR
    public Product(){
        generateGtin();
    }


    @XmlElement(name="price")
    public PriceTag getPriceTag() {
        return priceTag;
    }
    @XmlTransient
    public Date getOrderDate() {
        return orderDate;
    }
    @XmlTransient
    public Double getValue() {
        return value;
    }


    //METHODS
    private void generateGtin(){
        int minim = 48;    // 0 in ascii
        int maximum = 57; //  9 in ascii
        int stringLen = 15;//  length of the numeric string

        Random random = new Random();

        this.gtin = random.ints(minim,maximum)
                .limit(stringLen)
                .collect(StringBuilder::new,StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", orderDate=" + orderDate +
                ", gtin='" + gtin + '\'' +
                ", priceTag=" + priceTag +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}

