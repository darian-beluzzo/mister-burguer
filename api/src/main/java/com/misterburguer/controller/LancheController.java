package com.misterburguer.controller;

import com.misterburguer.application.service.LancheService;
import com.misterburguer.domain.CalculoLanche;
import com.misterburguer.domain.Lanche;
import com.misterburguer.infra.dto.CalculoLancheDTO;
import com.misterburguer.infra.dto.LancheDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/lanche")
class LancheController extends BaseResource<Lanche, Long, LancheDTO> {

    private final Logger log = LoggerFactory.getLogger(com.misterburguer.controller.PedidoController.class);

    private LancheService lancheService;

    @Autowired
    protected ModelMapper modelMapper;

    LancheController(final LancheService service) {
	super(service, Lanche.class, LancheDTO.class);
	lancheService = service;
    }

    @PostMapping("/calcular")
    ResponseEntity<CalculoLancheDTO> calcularLanche(@Valid @RequestBody Map<Long, Integer> pIngredientesQuantidade) {
	log.info("Request to calcular lanche: {}", pIngredientesQuantidade);

	CalculoLanche calculoLanche = lancheService.calcularLanche(pIngredientesQuantidade);

	CalculoLancheDTO calculoLancheDTO = modelMapper.map(calculoLanche, CalculoLancheDTO.class);

	return ResponseEntity.ok().body(calculoLancheDTO);
    }
}