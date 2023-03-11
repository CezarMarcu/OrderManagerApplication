package com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.OrderProcessor;

import com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.XmlHandler.XmlHandler;
import com.Aplicatie_Interviu_Marcu_Cezar.Models.Order;
import com.Aplicatie_Interviu_Marcu_Cezar.Models.Product;
import com.Aplicatie_Interviu_Marcu_Cezar.Models.SupplierProducts;

import javax.xml.bind.JAXBException;
import java.util.*;

public class OrderProcessor {

    private static final String ordersPath = "/Users/marcucezar/Desktop/Aplicatie_Interviu_Marcu_Cezar/Results/Orders/orders.xml";

    private static Map<String, List<Product>> getSupplierMap() throws JAXBException {
        List<Order> orders = XmlHandler.extractObjectFromXml(ordersPath);

        //Create the Map
        List<String>suppliers = orders
                .stream()
                .map(Order::getProducts)
                .flatMap(Collection::stream)
                .map(Product::getSupplier).distinct()
                .toList();

        List<List<Product>> supplierProducts = new ArrayList<>();
        for(String ignored :suppliers){
            supplierProducts.add(new ArrayList<>());
        }

        Map<String, List<Product>> supplierTable = new HashMap<>();
        for (int i = 0; i < suppliers.size(); i++) {
            supplierTable.put(suppliers.get(i), supplierProducts.get(i));
        }


        //Populate the created Map
        List<Product>products = orders
                .stream()
                .map(Order::getProducts)
                .flatMap(Collection::stream)
                .toList();

        String supplier;
        for(Product product : products){
            supplier = product.getSupplier();
            if(supplierTable.containsKey(supplier)){
                supplierTable.get(supplier).add(product);
            }
        }
        return supplierTable;
    }

    public static void ProcessOrders() throws Exception {
        Map<String, List<Product>> suppliersTable = OrderProcessor.getSupplierMap();
        List<SupplierProducts>supplierProducts = new ArrayList<>();
        for (Map.Entry<String,List<Product>> entry : suppliersTable.entrySet()){
//			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            supplierProducts.add(new SupplierProducts("23", entry.getKey(), entry.getValue()));
        }
        for(SupplierProducts sp :supplierProducts){
            XmlHandler.generateSupplierXmlFile(sp);
        }
    }


}
