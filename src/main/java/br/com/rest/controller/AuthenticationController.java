package br.com.rest.controller;

import org.springframework.security.core.AuthenticationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.rest.config.security.TokenService;
import br.com.rest.controller.dto.TokenDto;
import br.com.rest.controller.form.LoginForm;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
	
	private static final String AUTHENTICATION_TYPE = "Bearer";

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> autenticaUsuario(@RequestBody @Valid LoginForm form) {

		UsernamePasswordAuthenticationToken usernamePasswordAuthentication = form.converteParaAutenticacao();

		try{
			Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthentication);

			String token = tokenService.geraToken(authentication);
			
			return ResponseEntity.ok(new TokenDto(token, AUTHENTICATION_TYPE));
		}catch(AuthenticationException ae){
			return ResponseEntity.badRequest().build();
		}
	}

}
