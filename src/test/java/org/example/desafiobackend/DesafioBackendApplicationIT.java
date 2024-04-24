package org.example.desafiobackend;

import org.example.desafiobackend.model.Veiculo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DesafioBackendApplicationIT {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void inserirVeiculo() {
		Veiculo novoVeiculo = new Veiculo("Mustang", "Ford", "ICT7273", "amarelo"
				, "2022");
		Veiculo veiculo = restTemplate.postForObject("/veiculos", novoVeiculo, Veiculo.class);
		assertNotNull(veiculo);

		List<Veiculo> listaVeiculos = List.of(restTemplate.getForObject("/veiculos", Veiculo[].class));
		assertEquals(1, listaVeiculos.size());
	}

	@Test
	void excluirVeiculo(){
		restTemplate.postForObject("/veiculos",
				new Veiculo("S10 Blazer 4.3 V6", "GM - Chevrolet", "MZR4782", "prata"
						, "1998"),
				Veiculo.class);

		List<Veiculo> listaVeiculos = List.of(restTemplate.getForObject("/veiculos", Veiculo[].class));
		listaVeiculos.forEach(veiculo -> restTemplate.delete("/veiculos/{id}", veiculo.getId()));
		listaVeiculos = List.of(restTemplate.getForObject("/veiculos", Veiculo[].class));
		assertEquals(0, listaVeiculos.size());
	}

	@Test
	void obterVeiculos(){
		List<Veiculo> listaVeiculos = List.of(restTemplate.getForObject("/veiculos", Veiculo[].class));
		listaVeiculos.forEach(veiculo -> restTemplate.delete("/veiculos/{id}", veiculo.getId()));

		restTemplate.postForObject("/veiculos",
				new Veiculo("T-4 DESERT STORM 4x4 3.0 TB Int Diesel", "Troller", "HHH0350"
						, "verde", "2012"),
				Veiculo.class);
		restTemplate.postForObject("/veiculos",
				new Veiculo("AVENTADOR LP 700-4 ROADSTER", "LAMBORGHINI", "NET0736", "azul"
						, "2023"),
				Veiculo.class);
		restTemplate.postForObject("/veiculos",
				new Veiculo("Cerato 2.0 16V Aut.", "Kia Motors", "KXK9678", "bege"
						, "2020"),
				Veiculo.class);

		listaVeiculos = List.of(restTemplate.getForObject("/veiculos", Veiculo[].class));
		assertEquals(3, listaVeiculos.size());
	}

	@Test
	void atualizarVeiculo(){
		restTemplate.postForObject("/veiculos",
				new Veiculo("California F1 V8 460cv", "Ferrari", "NCH2853", "preta"
						, "2021"),
				Veiculo.class);

		List<Veiculo> listaVeiculos = List.of(restTemplate.getForObject("/veiculos", Veiculo[].class));
		Veiculo veiculoAtualizado = listaVeiculos.stream()
				.filter(veiculo -> veiculo.getModelo().equalsIgnoreCase("California F1 V8 460cv"))
				.findFirst()
				.get();
		veiculoAtualizado.setAno("2020");
		veiculoAtualizado.setCor("rosa");

		restTemplate.put("/veiculos", veiculoAtualizado);
		listaVeiculos = List.of(restTemplate.getForObject("/veiculos", Veiculo[].class));
		veiculoAtualizado = listaVeiculos.stream()
				.filter(veiculo -> veiculo.getModelo().equalsIgnoreCase("California F1 V8 460cv"))
				.findFirst()
				.get();

		assertEquals("rosa", veiculoAtualizado.getCor());
	}

}
