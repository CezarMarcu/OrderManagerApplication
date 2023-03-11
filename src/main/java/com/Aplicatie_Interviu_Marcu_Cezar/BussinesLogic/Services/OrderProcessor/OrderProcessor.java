package com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.OrderProcessor;

import com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.XmlHandler.XmlHandler;
import com.Aplicatie_Interviu_Marcu_Cezar.Models.Order;
import com.Aplicatie_Interviu_Marcu_Cezar.Models.Product;
import com.Aplicatie_Interviu_Marcu_Cezar.Models.SupplierProducts;

import javax.xml.bind.JAXBException;
import java.io.File;
//import java.nio.file.Path;
import java.util.*;

public class OrderProcessor {


    private static Map<String, List<Product>> getSupplierMap(File xmlFile) throws JAXBException {
        List<Order> orders = XmlHandler.extractObjectFromXml(xmlFile);

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

    public static void ProcessOrders(File xmlFile) throws Exception {
        Map<String, List<Product>> suppliersTable = OrderProcessor.getSupplierMap(xmlFile);
        List<SupplierProducts>supplierProducts = new ArrayList<>();
        for (Map.Entry<String,List<Product>> entry : suppliersTable.entrySet()){
            supplierProducts.add(new SupplierProducts("23", entry.getKey(), entry.getValue()));
        }
        for(SupplierProducts sp :supplierProducts){
            XmlHandler.generateSupplierXmlFile(sp);
        }
    }
}
