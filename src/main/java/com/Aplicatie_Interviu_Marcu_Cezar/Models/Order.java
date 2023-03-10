package com.Aplicatie_Interviu_Marcu_Cezar.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
public class Order {

    //ATRIBURES
    private  String created;
    private String id;
    private List<Product> products;


    //CONSTRUCTOR
    public Order (List<Product> products) {
        generateOrderId();
        this.created = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'dd'T'HH':'mm':'ss"));
        this.products = products;
    }
    public Order(){}


    //METHODS
    private void generateOrderId(){
        int minim = 48;    // 0 in ascii
        int maximum = 57; //  9 in ascii
        int stringLen = 4;//  length of the numeric string
        Random random = new Random();
        this.id = random.ints(minim,maximum)
                .limit(stringLen)
                .collect(StringBuilder::new,StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @XmlAttribute(name = "created")
    public String getCreated() {
        return created;
    }
    public void setCreated(String created) {
        this.created = created;
    }


    @XmlAttribute(name=("id"))
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    @XmlElement(name = "product")
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }


    @Override
    public String toString() {
        return "Order{" +
                "created='" + created + '\'' +
                ", id='" + id + '\'' +
                ", products=" + products +
                '}';
    }

}
