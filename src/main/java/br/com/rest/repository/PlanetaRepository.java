package br.com.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rest.model.Planeta;

public interface PlanetaRepository extends JpaRepository<Planeta, Long> {
	
	public Page<Planeta> findByNome(Pageable page, String nomePlaneta);

}
