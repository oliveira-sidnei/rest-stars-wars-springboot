package br.com.rest.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.mysql.cj.util.StringUtils;

public class AuthenticationTokenFilter extends OncePerRequestFilter{

	private static final String AUTHENTICATION_TYPE = "Bearer ";
	
	private TokenService tokenService;
	
	
	public AuthenticationTokenFilter(TokenService tokenService) {
	
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token  = recuperaToken(request.getHeader("Authorization"));
		
		System.out.println(tokenService.isValid(token));

		filterChain.doFilter(request, response);
		
	}

	private String recuperaToken(String header) {
		if(StringUtils.isNullOrEmpty(header) || !header.contains(AUTHENTICATION_TYPE))
			return null;

		String token = header.substring(7, header.length());
		
		return token == null || token.isEmpty() ? null : token;
	}

}
