package br.com.rest.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rest.controller.dto.PlanetaDto;
import br.com.rest.model.Planeta;

@RestController
public class PlanetaController {

	@RequestMapping("/planetas")
	public List<PlanetaDto> listaPlanetas() {

		return PlanetaDto.converteParaDto(Arrays.asList(
				new Planeta(1L, "Korriban", "Cold and Dry", "Mountains and Tombs"),
				new Planeta(2L, "Mustafar", "Hot", "Lava")));

	}
}
