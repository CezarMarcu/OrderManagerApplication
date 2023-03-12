package com.Aplicatie_Interviu_Marcu_Cezar.Models;


import lombok.Data;
import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "orders")
public class Orders {
    //ATTRIBUTES
    private List<Order> orders = new ArrayList<>();


    //CONSTRUCTOR
    public Orders(List<Order> orders) {
        this.orders = orders;
    }
    public Orders(){}


    //GETTERS AND SETTERS
    @XmlElement(name = "order", type = Order.class)
    public List<Order> getOrders() {
        return orders;
    }

}
