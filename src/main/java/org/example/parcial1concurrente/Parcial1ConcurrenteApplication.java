package org.example.parcial1concurrente;

import org.example.parcial1concurrente.service.ProduccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Parcial1ConcurrenteApplication {

	@Autowired
	private ProduccionService produccionService;

	public static void main(String[] args) {
		SpringApplication.run(Parcial1ConcurrenteApplication.class, args);
	}

	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() {
		try {
			produccionService.producirComponentes();
			produccionService.ensamblarMaquina();
			produccionService.asignarValoresDistribucion();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}