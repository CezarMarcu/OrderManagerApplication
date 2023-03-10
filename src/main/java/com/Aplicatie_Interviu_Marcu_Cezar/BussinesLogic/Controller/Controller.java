package com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Controller;

import com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.XmlHandler.XmlHandler;
import com.Aplicatie_Interviu_Marcu_Cezar.Models.Order;
import com.Aplicatie_Interviu_Marcu_Cezar.Models.Product;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/admin")
public class Controller {

    private  List<Product> products = new ArrayList<>();
    private  List<Order> orders = new ArrayList<>();

    @ModelAttribute
    public Product getProduct(){
        return new Product();
    }
    @ModelAttribute
    public Order getOrder(){
        return new Order(this.products);
    }


    @GetMapping
    public String showProducts(Model model){
        model.addAttribute("products", this.products);
        model.addAttribute("orders",this.orders);
        return "admin";
    }

    @PostMapping
    public String saveProducts(Product product){
        products.add(product);
        return "redirect:admin";
    }

    @PostMapping(params="placeOrder=true")
    public String placeOrder(Order order){
        List<Product> products = this.products;
        order.setProducts(products);
        orders.add(order);
        System.out.println("Order registered" + order);
        System.out.println("Your orders" + orders);
        System.out.println("-------------------------------------------");
//        this.products.clear();

        return "redirect:admin";
    }

    @PostMapping(params="generateXML=true")
    public String  generateXML()throws Exception{
        XmlHandler.generateXmlFile(orders);
        orders.clear();
        System.out.println("XML GENERATED WITH SUCCESS");
        return "redirect:admin";
    }

}
