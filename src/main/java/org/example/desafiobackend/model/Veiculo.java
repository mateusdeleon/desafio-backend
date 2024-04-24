package org.example.desafiobackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "veiculos")
@Data
@NoArgsConstructor
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "modelo", nullable = false)
    private String modelo;
    @Column(name = "marca", nullable = false)
    private String marca;
    @Column(name = "placa", nullable = false)
    private String placa;
    @Column(name = "cor")
    private String cor;
    @Column(name = "ano", nullable = false)
    private String ano;

    public Veiculo(String modelo, String marca, String placa, String cor, String ano) {
        this.modelo = modelo;
        this.marca = marca;
        this.placa = placa;
        this.cor = cor;
        this.ano = ano;
    }
}
