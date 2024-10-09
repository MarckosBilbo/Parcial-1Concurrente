package org.example.parcial1concurrente.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "maquinas")
@Getter
@Setter
public class Maquina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;  // Nombre de la máquina

    @OneToMany(mappedBy = "maquina", cascade = CascadeType.ALL)
    private List<Bola> bolas;  // Lista de bolas que han pasado por la máquina
}
