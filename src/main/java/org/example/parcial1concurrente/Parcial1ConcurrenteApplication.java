package org.example.parcial1concurrente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Parcial1ConcurrenteApplication {
	public static void main(String[] args) {
		SpringApplication.run(Parcial1ConcurrenteApplication.class, args);
	}
}