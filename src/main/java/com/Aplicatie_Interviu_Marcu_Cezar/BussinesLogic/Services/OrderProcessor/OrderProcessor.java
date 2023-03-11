package com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.OrderProcessor;

import com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.XmlHandler.XmlHandler;
import com.Aplicatie_Interviu_Marcu_Cezar.Models.Order;
import com.Aplicatie_Interviu_Marcu_Cezar.Models.Product;
import com.Aplicatie_Interviu_Marcu_Cezar.Models.SupplierProducts;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OrderProcessor {

    private static Map<String, ArrayList<Product>> getSupplierMap(File xmlFile) throws JAXBException {

        List<Order> orders = XmlHandler.extractObjectFromXml(xmlFile);
        //CREATE THE MAP
        List<String>suppliers = orders
                .stream()
                .map(Order::getProducts)
                .flatMap(Collection::stream)
                .map(Product::getSupplier).distinct()
                .toList();

        List<ArrayList<Product>>supplierProducts = suppliers.stream()
                .map((supplier)->new ArrayList<Product>())
                .toList();

        Map<String, ArrayList<Product>> supplierTable = IntStream.range(0, suppliers.size())
                .boxed()
                .collect(Collectors.toMap(suppliers::get, supplierProducts::get));

        //POPULATE THE CREATED MAP
        List<Product>products = orders
                .stream()
                .map(Order::getProducts)
                .flatMap(Collection::stream)
                .toList();

        for(Product product : products){
            if(supplierTable.containsKey(product.getSupplier())){
                supplierTable.get(product.getSupplier()).add(product);
            }
        }
        return supplierTable;
    }


    public static void ProcessOrders(File xmlFile) throws Exception {
        Map<String, ArrayList<Product>> suppliersTable = OrderProcessor.getSupplierMap(xmlFile);
        List<SupplierProducts>supplierProducts = new ArrayList<>();

        for (Map.Entry<String,ArrayList<Product>> entry : suppliersTable.entrySet()){
            supplierProducts.add(new SupplierProducts(xmlFile.getName().substring(5,7), entry.getKey(), entry.getValue()));
        }
        for(SupplierProducts sp :supplierProducts){
            XmlHandler.generateSupplierXmlFile(sp);
        }
    }
}
