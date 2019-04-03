package com.misterburguer.controller;

import com.misterburguer.application.service.IngredienteService;
import com.misterburguer.domain.Ingrediente;
import com.misterburguer.infra.dto.IngredienteDTO;
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
class IngredienteController {

    private final Logger log = LoggerFactory.getLogger(IngredienteController.class);

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private ModelMapper modelMapper;

    @DeleteMapping("/ingrediente/{id}")
    public ResponseEntity<?> deleteIngrediente(@PathVariable Long id) {
	log.info("Request to delete ingrediente: {}", id);
	ingredienteService.deleteById(id);
	return ResponseEntity.ok().build();
    }

    private IngredienteDTO convertToDTO(Ingrediente pIngrediente) {
	return modelMapper.map(pIngrediente, IngredienteDTO.class);
    }

    private Ingrediente convertToEntity(IngredienteDTO pIngredienteDTO) {
	return modelMapper.map(pIngredienteDTO, Ingrediente.class);
    }

    @PostMapping("/ingrediente")
    ResponseEntity<IngredienteDTO> createIngrediente(@Valid @RequestBody IngredienteDTO ingredienteDTO)
		    throws URISyntaxException {
	log.info("Request to create ingrediente: {}", ingredienteDTO);
	Ingrediente ingrediente;
	try {
	    ingrediente = convertToEntity(ingredienteDTO);
	} catch (RuntimeException e) {
	    return ResponseEntity.badRequest().body(ingredienteDTO);
	}

	Ingrediente result = ingredienteService.create(ingrediente);
	IngredienteDTO resultDTO = convertToDTO(result);
	return ResponseEntity.created(new URI("/api/ingrediente/" + resultDTO.getId()))
			.body(resultDTO);
    }

    @GetMapping("/ingrediente/{id}")
    ResponseEntity<?> getIngrediente(@PathVariable Long id) {
	Optional<Ingrediente> ingrediente = ingredienteService.findById(id);
	return ingrediente.map(response -> ResponseEntity.ok().body(response))
			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/ingrediente")
    Collection<IngredienteDTO> ingrediente() {
	List<Ingrediente> ingredientes = ingredienteService.findAll();
	return ingredientes.stream()
			.map(this::convertToDTO)
			.collect(Collectors.toList());
    }

    @PutMapping("/ingrediente")
    ResponseEntity<IngredienteDTO> updateIngrediente(@Valid @RequestBody Ingrediente ingrediente) {
	log.info("Request to update ingrediente: {}", ingrediente);
	Ingrediente result = ingredienteService.update(ingrediente);
	return ResponseEntity.ok().body(convertToDTO(result));
    }
}