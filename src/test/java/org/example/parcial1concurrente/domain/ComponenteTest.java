package org.example.parcial1concurrente.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComponenteTest {

    @Test
    public void testGettersAndSetters() {
        // Arreglar
        Componente componente = new Componente();
        componente.setId(1L);
        componente.setTipo("Clavo");
        componente.setNombre("Clavo de acero");

        // Actuar y Afirmar
        assertEquals(1L, componente.getId());
        assertEquals("Clavo", componente.getTipo());
        assertEquals("Clavo de acero", componente.getNombre());
    }

    @Test
    public void testDefaultConstructor() {
        // Arreglar y Actuar
        Componente componente = new Componente();

        // Afirmar
        assertNull(componente.getId());
        assertNull(componente.getTipo());
        assertNull(componente.getNombre());
    }
}