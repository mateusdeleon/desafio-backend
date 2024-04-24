package org.example.desafiobackend.service;

import jakarta.validation.constraints.NotNull;
import org.example.desafiobackend.model.Veiculo;
import org.example.desafiobackend.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> obterTodos() {
        return veiculoRepository.findAll();
    }

    public Veiculo obterVeiculoPeloId(Long idVeiculo){
        return veiculoRepository.findById(idVeiculo).orElseThrow(() -> new RuntimeException("Recurso não encontrado"));
    }

    public Veiculo inserirVeiculo(Veiculo veiculo) throws RuntimeException{
        if (veiculoRepository.existsByPlacaLikeIgnoreCase(veiculo.getPlaca()))
            throw new RuntimeException("Placa já cadastrada.");

        if (veiculoRepository
                .existsByModeloLikeIgnoreCaseAndMarcaLikeIgnoreCaseAndPlacaLikeIgnoreCaseAndAnoLikeIgnoreCase(
                    veiculo.getModelo(), veiculo.getMarca(), veiculo.getPlaca(), veiculo.getAno()))
            throw new RuntimeException("Veículo já cadastrado.");

        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizarVeiculo(@NotNull Veiculo veiculo) {
        Veiculo veiculoSalvo = obterVeiculoPeloId(veiculo.getId());
        veiculoSalvo.setModelo(veiculo.getModelo());
        veiculoSalvo.setMarca(veiculo.getMarca());
        veiculoSalvo.setPlaca(veiculo.getPlaca());
        veiculoSalvo.setCor(veiculo.getCor());
        veiculoSalvo.setAno(veiculo.getAno());

        return veiculoRepository.save(veiculoSalvo);
    }

    public void excluirVeiculoPeloId(Long idVeiculo) {
        obterVeiculoPeloId(idVeiculo);
        veiculoRepository.deleteById(idVeiculo);
    }
}
