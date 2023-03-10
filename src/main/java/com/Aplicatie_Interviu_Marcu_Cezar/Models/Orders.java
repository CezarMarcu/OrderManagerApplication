package com.Aplicatie_Interviu_Marcu_Cezar.Models;


import lombok.Data;
import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "orders")
public class Orders {

    private List<Order> orders = new ArrayList<>();

    public Orders(List<Order> orders) {
        this.orders = orders;
    }
    public Orders(){}

    @XmlElement(name = "order", type = Order.class)
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
