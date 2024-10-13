package org.example.parcial1concurrente.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BolaTest {

    @Test
    public void testGettersAndSetters() {
        // Arreglar: Crear un nuevo objeto Bola y establecer sus propiedades
        Bola bola = new Bola();
        bola.setId(1L);
        bola.setColor("Rojo");
        bola.setValorDistribucion(10.0);

        // Actuar y Afirmar: Verificar que las propiedades se establecieron correctamente
        assertEquals(1L, bola.getId());
        assertEquals("Rojo", bola.getColor());
        assertEquals(10.0, bola.getValorDistribucion());
    }

    @Test
    public void testDefaultConstructor() {
        // Arreglar y Actuar: Crear un nuevo objeto Bola usando el constructor por defecto
        Bola bola = new Bola();

        // Afirmar: Verificar que las propiedades son nulas por defecto
        assertNull(bola.getId());
        assertNull(bola.getColor());
        assertNull(bola.getValorDistribucion());
    }
}