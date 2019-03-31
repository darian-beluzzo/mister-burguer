package com.misterburguer.resource;

import com.misterburguer.domain.Ingrediente;
import com.misterburguer.domain.repository.IngredienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class IngredienteController {

    private final Logger log = LoggerFactory.getLogger(IngredienteController.class);

    private IngredienteRepository ingredienteRepository;

    public IngredienteController(IngredienteRepository ingredienteRepository) {
	this.ingredienteRepository = ingredienteRepository;
    }

    @DeleteMapping("/ingrediente/{id}")
    public ResponseEntity<?> deleteIngrediente(@PathVariable Long id) {
	log.info("Request to delete ingrediente: {}", id);
	ingredienteRepository.deleteById(id);
	return ResponseEntity.ok().build();
    }

    @PostMapping("/ingrediente")
    ResponseEntity<Ingrediente> createIngrediente(@Valid @RequestBody Ingrediente ingrediente) throws URISyntaxException {
	log.info("Request to create ingrediente: {}", ingrediente);
	Ingrediente result = ingredienteRepository.save(ingrediente);
	return ResponseEntity.created(new URI("/api/ingrediente/" + result.getId()))
			.body(result);
    }

    @GetMapping("/ingrediente/{id}")
    ResponseEntity<?> getIngrediente(@PathVariable Long id) {
	Optional<Ingrediente> ingrediente = ingredienteRepository.findById(id);
	return ingrediente.map(response -> ResponseEntity.ok().body(response))
			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/ingredientes")
    Collection<Ingrediente> ingredientes() {
	return ingredienteRepository.findAll();
    }

    @PutMapping("/ingrediente")
    ResponseEntity<Ingrediente> updateIngrediente(@Valid @RequestBody Ingrediente ingrediente) {
	log.info("Request to update ingrediente: {}", ingrediente);
	Ingrediente result = ingredienteRepository.save(ingrediente);
	return ResponseEntity.ok().body(result);
    }
}