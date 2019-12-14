package br.com.rest.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mysql.cj.util.StringUtils;

import br.com.rest.model.Usuario;
import br.com.rest.repository.UsuarioRepository;

public class AuthenticationTokenFilter extends OncePerRequestFilter{

	private static final String AUTHENTICATION_TYPE = "Bearer ";
	
	private TokenService tokenService;
	
	private UsuarioRepository repository;
	
	
	public AuthenticationTokenFilter(TokenService tokenService, UsuarioRepository repository) {
	
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token  = recuperaToken(request.getHeader("Authorization"));
		
		if(token != null && !token.isEmpty()) {
			Usuario usuario = repository.findById(tokenService.getUsuarioId(token)).get();

			SecurityContextHolder.getContext()
			.setAuthentication(new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()));
		}
		
		filterChain.doFilter(request, response);
		
	}

	private String recuperaToken(String header) {
		if(StringUtils.isNullOrEmpty(header) || !header.contains(AUTHENTICATION_TYPE))
			return null;

		String token = header.substring(7, header.length());
		
		return token == null || token.isEmpty() ? null : token;
	}
}