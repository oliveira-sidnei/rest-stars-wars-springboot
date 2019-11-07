package br.com.rest.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.rest.controller.dto.PlanetaDto;
import br.com.rest.controller.form.AtualizaPlanetaForm;
import br.com.rest.controller.form.PlanetaForm;
import br.com.rest.model.Planeta;
import br.com.rest.repository.PlanetaRepository;

@RestController
@RequestMapping(value = "/planetas")
public class PlanetaController {

	@Autowired
	private PlanetaRepository planetaRepository;

	@GetMapping
	public Page<PlanetaDto> listaPlanetas(@NotBlank @RequestParam(required = false) String nomePlaneta, 
			@PageableDefault(size = 2, page = 0)Pageable paginacao) {
		

		if(StringUtils.isEmpty(nomePlaneta))
			return PlanetaDto.converteParaDto(planetaRepository.findAll(paginacao));
		else
			return PlanetaDto.converteParaDto(planetaRepository.findByNome(paginacao, nomePlaneta));

	}

	@PostMapping
	public ResponseEntity<PlanetaDto> cadastrarPlaneta(@RequestBody @Valid PlanetaForm planetaForm, UriComponentsBuilder  uriBuilder) {

		Planeta planeta = PlanetaForm.converteParaPlaneta(planetaForm);

		planetaRepository.save(planeta);

		URI uri = uriBuilder.path("/planetas/{id}").buildAndExpand(planeta.getCodigo()).toUri();

		return ResponseEntity.created(uri).body(new PlanetaDto(planeta));
	}

	@GetMapping("{planetaID}")
	public ResponseEntity<PlanetaDto> buscaporId(@PathVariable @NumberFormat Long planetaID){

		Optional<Planeta> planeta = planetaRepository.findById(planetaID);

		if(!planeta.isPresent())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().body(new PlanetaDto(planeta.get()));
	}

	@PutMapping("/{planetaID}")
	@Transactional
	public ResponseEntity<PlanetaDto> atualizar(@PathVariable Long planetaID, @RequestBody @Valid AtualizaPlanetaForm form){

		Optional<Planeta> planetaOptional = planetaRepository.findById(planetaID);

		if(!planetaOptional.isPresent())
			return ResponseEntity.notFound().build();

		Planeta planeta = form.atualizar(planetaID, planetaRepository); 		

		return ResponseEntity.ok().body(new PlanetaDto(planeta));
	}

	@DeleteMapping("/{planetaID}")
	public ResponseEntity<?> remove(@PathVariable Long planetaID){

		Optional<Planeta> planetaOptional = planetaRepository.findById(planetaID);

		if(planetaOptional.isPresent()){
			planetaRepository.delete(planetaOptional.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();	
	}

}
