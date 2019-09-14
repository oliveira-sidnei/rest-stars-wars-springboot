package br.com.rest.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.rest.model.Planeta;

public class PlanetaDto {
	
	private Long codigo;
	private String nome;
	private String terreno;
	private String clima;
	
	
	
	
	public PlanetaDto(Planeta planeta) {
		this.codigo = planeta.getCodigo();
		this.nome = planeta.getNome();
		this.terreno = planeta.getTerreno();
		this.clima = planeta.getClima();
	}
	public Long getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}
	public String getTerreno() {
		return terreno;
	}
	public String getClima() {
		return clima;
	}
	public static List<PlanetaDto> converteParaDto(List<Planeta> planetas) {
		return planetas.stream().map(PlanetaDto :: new).collect(Collectors.toList());
		
	}
}