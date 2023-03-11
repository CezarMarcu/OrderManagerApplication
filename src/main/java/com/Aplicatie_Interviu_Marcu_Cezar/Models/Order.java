package com.Aplicatie_Interviu_Marcu_Cezar.Models;

import com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Formatters.DatetimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
public class Order {

    //ATRIBURES
    private Date created;
    private String id;
    private List<Product> products;


    //CONSTRUCTOR
    public Order (List<Product> products) {
        generateOrderId();
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        this.created = Date.from(instant);
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
    @XmlJavaTypeAdapter(DatetimeFormatter.class)
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
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
