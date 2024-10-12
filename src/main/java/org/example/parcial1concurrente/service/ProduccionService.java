package org.example.parcial1concurrente.service;

import org.example.parcial1concurrente.domain.Componente;
import org.example.parcial1concurrente.domain.Bola;
import org.example.parcial1concurrente.repos.ComponenteRepository;
import org.example.parcial1concurrente.repos.BolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class ProduccionService {
    private final ComponenteRepository componenteRepository;
    private final BolaRepository bolaRepository;
    private final BlockingQueue<Componente> bufferComponentes = new LinkedBlockingQueue<>();
    private final BlockingQueue<Bola> bufferBolas = new LinkedBlockingQueue<>();

    // Constructor con inyección de dependencias
    @Autowired
    public ProduccionService(ComponenteRepository componenteRepository, BolaRepository bolaRepository) {
        this.componenteRepository = componenteRepository;
        this.bolaRepository = bolaRepository;
    }

    // metodo asíncrono para producir componentes(para un "tablero" específico)
    @Async
    public void producirComponentes() {
        // Producción de 100 bolas
        for (int i = 0; i < 100; i++) {
            Componente bola = new Componente();
            bola.setTipo("Bola");
            bola.setNombre("Bola " + i);
            componenteRepository.save(bola);
            bufferComponentes.add(bola);
            System.out.println("Bola " + i + " producida.");
        }

        // Producción de 15 clavos
        for (int i = 0; i < 15; i++) {
            Componente clavo = new Componente();
            clavo.setTipo("Clavo");
            clavo.setNombre("Clavo " + i);
            componenteRepository.save(clavo);
            bufferComponentes.add(clavo);
            System.out.println("Clavo " + i + " producido.");
        }

        // Producción de 6 contenedores
        for (int i = 0; i < 6; i++) {
            Componente contenedor = new Componente();
            contenedor.setTipo("Contenedor");
            contenedor.setNombre("Contenedor " + i);
            componenteRepository.save(contenedor);
            bufferComponentes.add(contenedor);
            System.out.println("Contenedor " + i + " producido.");
        }
    }

    // metodo asíncrono para ensamblar la máquina
    @Async
    public void ensamblarMaquina() throws InterruptedException {
        while (true) {
            Componente componente = bufferComponentes.take();
            System.out.println("Ensamblando " + componente.getNombre());
            // Lógica de ensamblaje
        }
    }

    // metodo asíncrono para asignar valores de distribución a las bolas
    @Async
    public void asignarValoresDistribucion(String filePath) throws IOException, InterruptedException {
        List<Double> valoresDistribucion = leerValoresDistribucion(filePath);
        for (int i = 0; i < valoresDistribucion.size(); i++) {
            Bola bola = new Bola();
            bola.setColor("Color " + i);
            bola.setValorDistribucion(valoresDistribucion.get(i));
            bolaRepository.save(bola);
            bufferBolas.add(bola);
            System.out.println("Bola " + i + " con valor de distribución " + valoresDistribucion.get(i) + " asignado.");
        }
    }

    // metodo para leer los valores de distribución desde un archivo (.CSV)
    private List<Double> leerValoresDistribucion(String filePath) throws IOException {
        List<Double> valores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Saltar la primera línea (cabecera)
            while ((line = br.readLine()) != null) {
                valores.add(Double.parseDouble(line));
            }
        }
        return valores;
    }

    // metodo para consumir una bola del buffer
    public Bola consumirBola() throws InterruptedException {
        return bufferBolas.take();
    }
}