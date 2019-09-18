package br.com.rest.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.rest.model.Planeta;
import br.com.rest.repository.PlanetaRepository;

public class AtualizaPlanetaForm {

	@NotBlank
	@Size(max = 50)
	private String clima;

	@NotBlank
	@Size(max = 50)
	private String terreno;

	public Planeta atualizar(Long planetaID, PlanetaRepository planetaRepository) {

		Planeta planeta = planetaRepository.getOne(planetaID);
		planeta.setClima(clima);
		planeta.setTerreno(terreno);

		return planeta;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}	
}
