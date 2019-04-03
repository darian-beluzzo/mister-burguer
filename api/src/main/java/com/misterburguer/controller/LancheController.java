package com.misterburguer.controller;

import com.misterburguer.application.service.LancheService;
import com.misterburguer.domain.Lanche;
import com.misterburguer.infra.dto.LancheDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
class LancheController {

    private final Logger log = LoggerFactory.getLogger(LancheController.class);

    @Autowired
    private LancheService lancheService;

    @Autowired
    private ModelMapper modelMapper;

    public LancheController() {
    }

    @DeleteMapping("/lanche/{id}")
    public ResponseEntity<?> deleteLanche(@PathVariable Long id) {
	log.info("Request to delete lanche: {}", id);
	lancheService.deleteById(id);
	return ResponseEntity.ok().build();
    }

    private LancheDTO convertToDTO(Lanche pLanche) {
	return modelMapper.map(pLanche, LancheDTO.class);
    }

    private Lanche convertToEntity(LancheDTO pLancheDTO) {
	return modelMapper.map(pLancheDTO, Lanche.class);
    }

    @PostMapping("/lanche")
    ResponseEntity<Lanche> createLanche(@Valid @RequestBody Lanche lanche) throws URISyntaxException {
	log.info("Request to create lanche: {}", lanche);
	Lanche result = lancheService.create(lanche);
	return ResponseEntity.created(new URI("/api/lanche/" + result.getId()))
			.body(result);
    }

    @GetMapping("/lanche/{id}")
    ResponseEntity<?> getLanche(@PathVariable Long id) {
	Optional<Lanche> lanche = lancheService.findById(id);
	return lanche.map(response -> ResponseEntity.ok().body(response))
			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/lanche")
    Collection<LancheDTO> lanche() {
	//	List<Lanche> all = lancheService.getList();
	//	BigDecimal valor;
	//	for (Lanche lanche : all) {
	//	    valor = BigDecimal.ZERO;
	//	    List<Ingrediente> lanches = lanche.getIngredientes();
	//	    for (Ingrediente ingrediente : lanches) {
	//		valor = valor.add(ingrediente.getValor());
	//	    }
	//	    //	    lanche.setValor(valor);
	//	}
	//	return all;
	List<Lanche> lanches = lancheService.findAll();
	return lanches.stream()
			.map(this::convertToDTO)
			.collect(Collectors.toList());
    }

    @PutMapping("/lanche")
    ResponseEntity<Lanche> updateLanche(@Valid @RequestBody Lanche lanche) {
	log.info("Request to update lanche: {}", lanche);
	Lanche result = lancheService.update(lanche);
	return ResponseEntity.ok().body(result);
    }
}