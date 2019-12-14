package br.com.rest.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.rest.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	
	@Value("${apisw.json.secret}")
	private String secret;
	
	@Value("${apisw.json.expiration}")
	private String expiration;
	
	
	public String geraToken(Authentication authentication) {
		
		Usuario usuario = (Usuario) authentication.getPrincipal();
		
		Date dataAtual = new Date();
		
		Date dataExpiracao = new Date(dataAtual.getTime() + new Long(expiration));
		
		return Jwts.builder().setExpiration(dataExpiracao)
				.setIssuedAt(dataAtual)
				.setIssuer("APISW")
				.setSubject(usuario.getCodigo().toString())
				.signWith(SignatureAlgorithm.HS256, secret).compact();
		
	}
	
	public boolean isValid(String token) {

		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		} 
	}

	public Long getUsuarioId(String token) {
		if(token == null || token.isEmpty()) return null;
			
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims.getSubject() == null ? null : new Long(claims.getSubject());
	}
}
