package com.Aplicatie_Interviu_Marcu_Cezar.Models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
public class SupplierProducts {

    private String fromOrder;


    private String toSupplier;
    private List<Product> products = new ArrayList<>();

    public SupplierProducts(String fromOrder,String toSupplier, List<Product> products) {
        this.fromOrder = fromOrder;
        this.toSupplier = toSupplier;
        this.products = products;
    }
    public SupplierProducts(){}

    @XmlElement(name = "fromOrder")
    public String getFromOrder() {
        return fromOrder;
    }
    public void setFromOrder(String fromOrder) {
        this.fromOrder = fromOrder;
    }

    @XmlElement(name="product")
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getToSupplier() {
        return toSupplier;
    }
    public void setToSupplier(String toSupplier) {
        this.toSupplier = toSupplier;
    }

    @Override
    public String toString() {
        return "SupplierProducts{" +
                "fromOrder='" + fromOrder + '\'' +
                ", toSupplier='" + toSupplier + '\'' +
                ", products=" + products +
                '}';
    }
}

