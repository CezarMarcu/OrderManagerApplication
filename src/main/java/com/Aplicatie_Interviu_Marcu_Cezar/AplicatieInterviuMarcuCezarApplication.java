package com.Aplicatie_Interviu_Marcu_Cezar;

import com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.RunnableClasses.DirectoryWatchRunnable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class AplicatieInterviuMarcuCezarApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplicatieInterviuMarcuCezarApplication.class, args);
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.submit(new DirectoryWatchRunnable());
	}

}
