package com.misterburguer.domain.repository;

import com.misterburguer.domain.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

}