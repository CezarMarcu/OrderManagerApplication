package com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Controller;

import com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.XmlHandler.XmlHandler;
import com.Aplicatie_Interviu_Marcu_Cezar.Models.Order;
import com.Aplicatie_Interviu_Marcu_Cezar.Models.Product;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/admin")
public class Controller {

    private  List<Product> products = new ArrayList<>();
    private  final List<Order> orders = new ArrayList<>();

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
    public String saveProducts(@Valid Product product, Errors errors){
        if(!(errors.hasErrors())){
            products.add(product);
            return "redirect:admin";
        }
        return "admin";
    }

    @PostMapping(params="placeOrder=true")
    public String placeOrder(Order order) {
        order.setProducts(products);
        orders.add(order);
        return "redirect:admin";
    }

    @PostMapping(params="generateXML=true")
    public String  generateXML()throws Exception{
        XmlHandler.generateXmlFile(orders);
        orders.clear();
        products.clear();
        return "redirect:admin";
    }
}
