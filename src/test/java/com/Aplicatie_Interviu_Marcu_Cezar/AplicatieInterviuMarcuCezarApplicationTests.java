package com.Aplicatie_Interviu_Marcu_Cezar;

import com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.DirectoryWatcher.DirectoryWatcher;
import com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.XmlHandler.XmlHandler;
import com.Aplicatie_Interviu_Marcu_Cezar.Models.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.*;
import javax.xml.bind.Unmarshaller;


@SpringBootTest
class AplicatieInterviuMarcuCezarApplicationTests {

	@Test
	void generateId() {
		int minim = 48;
		int maximum = 57;
		int stringLen = 4;

		Random random = new Random();

		String generatedId = random.ints(minim,maximum)
				.limit(stringLen)
				.collect(StringBuilder::new,StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		System.out.println(generatedId);
	}

	@Test
	void OrderClassTest(){
		List<Product>products = new ArrayList<>();
		products.add(new Product("MacBook Pro M1", 100L, "Apple"));
		products.add(new Product("MacBook Pro M2", 200L, "Apple"));
		Order order = new Order(products);
		System.out.println(order);
	}

	@Test
	void ProductClassTest(){
		Product product = new Product("MacBook", 100L,"Apple");
		System.out.println(product);
	}

	@Test
	void generateXmlFile()throws Exception{
		JAXBContext contextObj1 = JAXBContext.newInstance(Orders.class);
		Marshaller marshallerObj1 = contextObj1.createMarshaller();
		marshallerObj1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		List<Product> products = new ArrayList<>();

		products.add(new Product("MacBook",100L,"Apple"));
		products.add(new Product("MacBook",100L,"Apple"));
		products.add(new Product("MacBook",100L,"Apple"));

		List<Order> orders = new ArrayList<>();
		orders.add(new Order(products));
		orders.add(new Order(products));
		orders.add(new Order(products));

		Orders ordersList=new Orders(orders);
		marshallerObj1.marshal(ordersList, new FileOutputStream("Results/Orders/orders.xml"));
	}

	@Test
	void watchDirectory(){
		String directory = "C:\\Users\\marcu_c1\\Desktop\\demo\\Results\\Orders";
		DirectoryWatcher directoryWatcher = new DirectoryWatcher(directory);
		directoryWatcher.myWatchBegins();
	}

	@Test
	void getObjectFromXml()throws Exception{
		String xmlFile = "C:\\Users\\marcu_c1\\Desktop\\demo\\Results\\Orders\\orders.xml";
		File file = new File(xmlFile);
		JAXBContext jaxbContext = JAXBContext.newInstance(Orders.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Orders orders= (Orders) jaxbUnmarshaller.unmarshal(file);
		List<Order>myOrders = orders.getOrders();
		System.out.println("YOUR ORDERS:\n" + myOrders);

		myOrders.stream()
				.map(Order::getProducts)
				.forEach(System.out::println );
	}

	@Test
	void getSuppliersFromOrderList() throws JAXBException {
		String ordersFile = "/Users/marcucezar/Desktop/Aplicatie_Interviu_Marcu_Cezar/Results/Orders/orders.xml";
		File xmlFile = new File(ordersFile);
		List<String>suppliers = XmlHandler.extractObjectFromXml(xmlFile)
				.stream()
				.map(Order::getProducts)
				.flatMap(Collection::stream)
				.map(Product::getSupplier).distinct()
				.toList();
		System.out.println(suppliers);


		List<List<Product>> supplierProducts = new ArrayList<>();
		for(String ignored :suppliers){
			supplierProducts.add(new ArrayList<>());
		}
		System.out.println(supplierProducts);


		Map<String, List<Product>> supplierTable = new HashMap<>();
		for (int i = 0; i < suppliers.size(); i++) {
			supplierTable.put(suppliers.get(i), supplierProducts.get(i));
		}
		System.out.println(supplierTable);
	}

}
