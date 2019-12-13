package br.com.rest.controller.form;

import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
	
	@NotBlank
	private String usuario;
	
	@NotBlank
	private String senha;

	
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "LoginForm [usuario=" + usuario + ", senha=" + senha + "]";
	}

	public UsernamePasswordAuthenticationToken converteParaAutenticacao() {
		return new UsernamePasswordAuthenticationToken(usuario, senha);
	}
}
