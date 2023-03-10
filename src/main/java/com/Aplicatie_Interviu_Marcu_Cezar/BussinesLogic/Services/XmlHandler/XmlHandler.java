package com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.XmlHandler;

import com.Aplicatie_Interviu_Marcu_Cezar.Models.Order;
import com.Aplicatie_Interviu_Marcu_Cezar.Models.Orders;
import com.Aplicatie_Interviu_Marcu_Cezar.Models.SupplierProducts;
import com.mifmif.common.regex.Generex;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class XmlHandler {

    private static String generateFileName(){
        Generex generex = new Generex("orders[0-9]{2}");
        return generex.random();
    }

    public static void generateXmlFile(List<Order> orders)throws Exception{
        JAXBContext contextObj1 = JAXBContext.newInstance(Orders.class);
        Marshaller marshallerObj1 = contextObj1.createMarshaller();
        marshallerObj1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        System.out.println(orders);
        Orders ordersList=new Orders(orders);
        System.out.println(ordersList);
        marshallerObj1.marshal(ordersList, new FileOutputStream("Results/Orders/"+XmlHandler.generateFileName()+".xml"));
    }


    //TO DO
    //Extract objects from XML file
    public static List<Order> extractObjectFromXml(String xmlFile) throws JAXBException {
        File file = new File(xmlFile);
        JAXBContext jaxbContext = JAXBContext.newInstance(Orders.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Orders orders= (Orders) jaxbUnmarshaller.unmarshal(file);
        return orders.getOrders();
    }

    //TO DO
    private void generateSupplierXmlFile(SupplierProducts supplierProducts) throws JAXBException {
        JAXBContext contextObj = JAXBContext.newInstance(Orders.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        SupplierProducts supplierProducts1 = new SupplierProducts();
    }


    //TO DO
    //Generate XML file for every supplier with sorted products by price
    public static void generateSupplierFiles(List<SupplierProducts> supplierProducts){}

    //TO DO
    //public static void processOrders(){}
}
