package br.com.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rest.controller.dto.PlanetaDto;
import br.com.rest.model.Planeta;
import br.com.rest.repository.PlanetaRepository;

@RestController
public class PlanetaController {

	@Autowired
	private PlanetaRepository planetaRepository;

	@RequestMapping("/planetas")
	public List<PlanetaDto> listaPlanetas(String nomePlaneta) {
		List<Planeta> listaPlanetas = new ArrayList<Planeta>();

		if(nomePlaneta == null || nomePlaneta.trim().isEmpty())
			listaPlanetas = planetaRepository.findAll();
		else
			listaPlanetas = planetaRepository.findByNome(nomePlaneta);

			return PlanetaDto.converteParaDto(listaPlanetas);
	}
}
