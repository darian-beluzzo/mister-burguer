package com.misterburguer.controller;

import com.misterburguer.application.service.IngredienteService;
import com.misterburguer.domain.Ingrediente;
import com.misterburguer.infra.dto.IngredienteDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ingrediente")
class IngredienteController extends BaseResource<Ingrediente, Long, IngredienteDTO> {

    IngredienteController(final IngredienteService service) {
	super(service, Ingrediente.class, IngredienteDTO.class);
    }

}