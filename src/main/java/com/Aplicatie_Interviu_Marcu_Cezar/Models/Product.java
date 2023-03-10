package com.Aplicatie_Interviu_Marcu_Cezar.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Random;

@Data
@AllArgsConstructor
public class Product {

    //ATTRIBUTES
    private String description;
    private String gtin;
    private Long price;
    private String supplier;


    //CONSTRUCTOR
    public Product(String description, Long price, String supplier){
        this.description = description;
        generateGtin();
        this.price = price;
        this.supplier = supplier;
    }
    public Product(){
        generateGtin();
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
        return "\n\tproduct{" +
                "description='" + description + '\'' +
                ", gtin='" + gtin + '\'' +
                ", price=" + price +
                ", supplier='" + supplier + '\'' +
                '}';
    }

}
