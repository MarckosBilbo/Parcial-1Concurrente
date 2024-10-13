package org.example.parcial1concurrente;

import org.example.parcial1concurrente.service.ProduccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;
import java.util.logging.Logger;

@SpringBootApplication
@EnableAsync
public class Parcial1ConcurrenteApplication {

	@Autowired
	private ProduccionService produccionService;

	private static final Logger logger = Logger.getLogger(Parcial1ConcurrenteApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(Parcial1ConcurrenteApplication.class, args);
		logger.info("Spring Boot application started");
	}

	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() {
		logger.info("Context refreshed event triggered");
		try {
			produccionService.producirComponentes();
			produccionService.ensamblarMaquina();
			produccionService.asignarValoresDistribucion();

			// Forzamos la apertura del navegador
			String url = "http://localhost:8080";
			openBrowser(url);
		} catch (Exception e) {
			logger.severe("Failed to open browser: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void openBrowser(String url) throws IOException {
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
		} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			Runtime.getRuntime().exec("open " + url);
		} else if (System.getProperty("os.name").toLowerCase().contains("nix") ||
				System.getProperty("os.name").toLowerCase().contains("nux")) {
			Runtime.getRuntime().exec("xdg-open " + url);
		} else {
			logger.warning("Unsupported operating system");
		}
	}
}
