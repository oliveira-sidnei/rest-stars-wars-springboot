package br.com.rest.controller.dto;

public class TokenDto {
	
	private String token;
	
	private String tipoAutenticacao;

	public TokenDto(String token, String tipoAutenticacao) {
	
		this.token = token;
		this.tipoAutenticacao = tipoAutenticacao;
	}

	public String getToken() {
		return token;
	}

	public String getTipoAutenticacao() {
		return tipoAutenticacao;
	}
}
