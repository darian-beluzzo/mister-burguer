package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.IngredienteService;
import com.misterburguer.domain.Ingrediente;
import com.misterburguer.domain.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
@Service
public class IngredienteServiceImpl implements IngredienteService {

    @Autowired
    IngredienteRepository ingredienteRepository;

    List<Ingrediente> findAll() {
	return ingredienteRepository.findAll();
    }
}
