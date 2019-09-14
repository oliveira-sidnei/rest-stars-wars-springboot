package br.com.rest.model;

public class Planeta {
	

	private Long codigo;
	private String nome;
	private String clima;
	private String terreno;

	private int quantidadeFilmes;
	
	
	
	public Planeta(Long codigo, String nome, String clima, String terreno) {
		this.codigo = codigo;
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
	}
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

	public Long getCodigo() {
		return codigo;
	}
	public int getQuantidadeFilmes() {
		return quantidadeFilmes;
	}
	public void setQuantidadeFilmes(int quantidadeFilmes) {
		this.quantidadeFilmes = quantidadeFilmes;
	}
}