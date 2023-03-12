package com.Aplicatie_Interviu_Marcu_Cezar;

import com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.DirectoryWatcher.DirectoryWatcher;
import com.Aplicatie_Interviu_Marcu_Cezar.Models.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.xml.bind.JAXBContext;
import java.io.File;
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
	void watchDirectory(){
		String directory = "path_to_xml_file";
		DirectoryWatcher directoryWatcher = new DirectoryWatcher(directory);
		directoryWatcher.myWatchBegins();
	}

	@Test
	void getObjectFromXml()throws Exception{
		String xmlFile = "path_to_xml_file";
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
	void getIdOfOrdersFile(){
		String ordersFile = "path_to_xml_file";
		File file = new File(ordersFile);
		String fileId = file.getName().substring(6,8);
		System.out.println(fileId);
	}

	@Test
	void getActualLocation(){
		String location = System.getProperty("user.dir");
		System.out.println(location);
	}

}
