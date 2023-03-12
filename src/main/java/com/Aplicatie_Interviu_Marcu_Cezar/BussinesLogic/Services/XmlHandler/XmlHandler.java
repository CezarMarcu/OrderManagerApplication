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

    //Method that generates a name for orders xml file that respects a regex
    private static String generateFileName(){
        Generex generex = new Generex("order[0-9]{2}");
        return generex.random();
    }

    //Method that generates the xml file which contains the orders
    public static void generateXmlFile(List<Order> orders)throws Exception{
        JAXBContext contextObj1 = JAXBContext.newInstance(Orders.class);
        Marshaller marshallerObj1 = contextObj1.createMarshaller();
        marshallerObj1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Orders ordersList=new Orders(orders);
        marshallerObj1.marshal(ordersList,
                new FileOutputStream("Results/Orders/"+XmlHandler.generateFileName()+".xml"));
    }

    //Method that generates a xml file for supplier
    public static void generateSupplierXmlFile(SupplierProducts supplierProducts) throws Exception {
            JAXBContext contextObj = JAXBContext.newInstance(SupplierProducts.class);
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallerObj.marshal(supplierProducts,
                    new FileOutputStream(
                            System.getProperty("user.dir") +"/Results/Suppliers/"
                            +supplierProducts.getToSupplier()
                            +supplierProducts.getFromOrder()+".xml")
            );
    }

    //Method that extracts the list of orders from xml file
    public static List<Order> extractObjectFromXml(File xmlFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Orders.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Orders orders= (Orders) jaxbUnmarshaller.unmarshal(xmlFile);
        return orders.getOrders();
    }
}
