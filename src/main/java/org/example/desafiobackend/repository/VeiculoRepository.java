package org.example.desafiobackend.repository;

import org.example.desafiobackend.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    boolean existsByPlacaLikeIgnoreCase(String placa);
    boolean existsByModeloLikeIgnoreCaseAndMarcaLikeIgnoreCaseAndPlacaLikeIgnoreCaseAndAnoLikeIgnoreCase(
            String modelo, String marca, String placa, String ano);
}