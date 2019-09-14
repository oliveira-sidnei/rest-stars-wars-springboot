package br.com.rest.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.rest.model.Planeta;

public class PlanetaForm {

	@NotBlank
	@Size(max = 50)
	private String nome;

	@NotBlank
	@Size(max = 50)
	private String clima;

	@NotBlank
	@Size(max = 50)
	private String terreno;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public static Planeta converteParaPlaneta(PlanetaForm planetaForm) {
		return new Planeta(planetaForm.getNome(), planetaForm.getClima(), planetaForm.getTerreno());
	}
}