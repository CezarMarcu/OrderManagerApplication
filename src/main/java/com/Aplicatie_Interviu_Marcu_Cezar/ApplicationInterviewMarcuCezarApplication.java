package com.Aplicatie_Interviu_Marcu_Cezar;

import com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.DirectoryWatcher.DirectoryWatcher;
import com.Aplicatie_Interviu_Marcu_Cezar.Config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*====================================================================================================================
													DOCUMENTATION
====================================================================================================================*/
/*	A backend application for order management,that takes as input a xml file with orders provided by a retailer (client)
*  and for every product from the input file it creates a xml file for every supplier of the respective product as output.
* The application has the following components:
*
* 		--> Frontend component, where the client can create new orders.
* 			The interface can be accessed using the following link "localhost:8080/admin".
* 			Libraries:
* 				->Bootstrap: for styling part
* 				->Thymeleaf: for templating the html document
*
*
* 		-->Backend component,where the orders are processed. The backend component has 4 subcomponents:
* 				Controller: Where the GET and POST requests are processed.
*
* 							LIBRARIES:
* 								-> SpringBoot: for developing the application.
* 								-> Validation: for validating the date came from input forms.
* 								-> Lombok: for creating classes with less boilerplate code.
*
* 				XML file Handler:
* 							Which has 4 functionalities:
* 								-generate a suitable name for the input xml file by using a regex pattern;
* 								-generate the input xml file by parsing the list of recorded orders into xml data format;
* 								-generates the output xml file by parsing the list of products for every supplier;
* 								-returning the list of orders by unmarshalling the input xml file.
*
* 							LIBRARIES:
 * 								-> Javax: for xml interaction
 * 								-> Generex: for generating a string based on a regex pattern
*
* 				Order processor:
* 								This component takes the xml file and uses the Xml handler component to get the
* 							list of orders object.
*
* 								After gets the list of orders from the xml file it creates a map of suppliers where
* 							the key is represented by the supplier and the value is represented by a list of product
* 							objects where the products, which got the supplier equal with the key, can be found.
*
* 								After gets the supplier map it extracts every value from map, and for each value creates
* 							a SupplierProducts object, after that for each SupplierProduct object it will generate an
* 							output xml file.
*
* 				DirectoryWatcher:
* 								Which supervises the directory of input files.
* 								It uses a while loop to listen the events of type "CREATE FILE".
*
* 								When an event of type "CREATE FILE" has occurred, the new created input xml file will
* 							be processed.
*
* 								To avoid the blocking of the main thread while this component listens for events, I
* 							chose to run this component on a separate thread.
*
*		Libraries used for testing:
* 				->JUnit
* */
@SpringBootApplication
public class ApplicationInterviewMarcuCezarApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationInterviewMarcuCezarApplication.class, args);
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.submit(() -> {
			DirectoryWatcher directoryWatcher = new DirectoryWatcher(Config.ORDERS);
			directoryWatcher.myWatchBegins();
		});
	}
}
