package br.com.rest.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.rest.controller.dto.PlanetaDto;
import br.com.rest.controller.form.PlanetaForm;
import br.com.rest.model.Planeta;
import br.com.rest.repository.PlanetaRepository;

@RestController
@RequestMapping(value = "/planetas")
public class PlanetaController {

	@Autowired
	private PlanetaRepository planetaRepository;

	@GetMapping
	public List<PlanetaDto> listaPlanetas(@NotBlank String nomePlaneta) {
		List<Planeta> listaPlanetas = new ArrayList<Planeta>();

		if(nomePlaneta == null || nomePlaneta.trim().isEmpty())
			listaPlanetas = planetaRepository.findAll();
		else
			listaPlanetas = planetaRepository.findByNome(nomePlaneta);

			return PlanetaDto.converteParaDto(listaPlanetas);
	}
	
	@PostMapping
	public ResponseEntity<PlanetaDto> cadastrarPlaneta(@RequestBody @Valid PlanetaForm planetaForm, UriComponentsBuilder  uriBuilder) {

		Planeta planeta = PlanetaForm.converteParaPlaneta(planetaForm);
		
		planetaRepository.save(planeta);
		
		URI uri = uriBuilder.path("/planetas/{id}").buildAndExpand(planeta.getCodigo()).toUri();

		return ResponseEntity.created(uri).body(new PlanetaDto(planeta));
	}
}
