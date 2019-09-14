package br.com.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "PLANETA")
public class Planeta {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO")
	private Long codigo;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "NOME")
	private String nome;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "CLIMA")
	private String clima;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "TERRENO")
	private String terreno;

	@Transient
	private int quantidadeFilmes;
	
	
	
	
	public Planeta() {}
	
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