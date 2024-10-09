package org.example.parcial1concurrente.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "estaciones_de_produccion")
@Getter
@Setter
public class EstacionDeProduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;  // Nombre de la estación

    @Column(nullable = false)
    private int cantidadMaxima;  // Cantidad máxima de bolas que puede producir

    @OneToMany(mappedBy = "estacionDeProduccion", cascade = CascadeType.ALL)
    private List<Bola> bolasProducidas;  // Lista de bolas producidas por esta estación
}
