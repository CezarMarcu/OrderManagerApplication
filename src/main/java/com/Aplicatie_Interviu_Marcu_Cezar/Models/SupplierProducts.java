package com.Aplicatie_Interviu_Marcu_Cezar.Models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
public class SupplierProducts {
    //ATTRIBUTES
    private String fromOrder;
    private String toSupplier;
    private List<Product> products = new ArrayList<>();


    //CONSTRUCTORS
    public SupplierProducts(String fromOrder,String toSupplier, List<Product> products) {
        this.fromOrder = fromOrder;
        this.toSupplier = toSupplier;
        this.products = products;
    }
    public SupplierProducts(){}


    //GETTERS and SETTERS
    @XmlTransient
    public String getFromOrder() {
        return fromOrder;
    }

    @XmlElement(name="product")
    public List<Product> getProducts() {
        return products;
    }

    @XmlTransient
    public String getToSupplier() {
        return toSupplier;
    }


    //METHODS
    @Override
    public String toString() {
        return "SupplierProducts{" +
                "fromOrder='" + fromOrder + '\'' +
                ", toSupplier='" + toSupplier + '\'' +
                ", products=" + products +
                '}';
    }
}

