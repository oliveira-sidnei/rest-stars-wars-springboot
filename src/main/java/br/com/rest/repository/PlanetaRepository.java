package br.com.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rest.model.Planeta;

public interface PlanetaRepository extends JpaRepository<Planeta, Long> {
	
	public List<Planeta> findByNome(String nomePlaneta);

}
