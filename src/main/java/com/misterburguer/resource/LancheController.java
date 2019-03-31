package com.misterburguer.resource;

import com.misterburguer.domain.Lanche;
import com.misterburguer.domain.repository.LancheRepository;
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
class LancheController {

    private final Logger log = LoggerFactory.getLogger(LancheController.class);

    private LancheRepository lancheRepository;

    public LancheController(LancheRepository lancheRepository) {
	this.lancheRepository = lancheRepository;
    }

    @DeleteMapping("/lanche/{id}")
    public ResponseEntity<?> deleteLanche(@PathVariable Long id) {
	log.info("Request to delete lanche: {}", id);
	lancheRepository.deleteById(id);
	return ResponseEntity.ok().build();
    }

    @PostMapping("/lanche")
    ResponseEntity<Lanche> createLanche(@Valid @RequestBody Lanche lanche) throws URISyntaxException {
	log.info("Request to create lanche: {}", lanche);
	Lanche result = lancheRepository.save(lanche);
	return ResponseEntity.created(new URI("/api/lanche/" + result.getId()))
			.body(result);
    }

    @GetMapping("/lanche/{id}")
    ResponseEntity<?> getLanche(@PathVariable Long id) {
	Optional<Lanche> lanche = lancheRepository.findById(id);
	return lanche.map(response -> ResponseEntity.ok().body(response))
			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/lanches")
    Collection<Lanche> lanches() {
	return lancheRepository.findAll();
    }

    @PutMapping("/lanche")
    ResponseEntity<Lanche> updateLanche(@Valid @RequestBody Lanche lanche) {
	log.info("Request to update lanche: {}", lanche);
	Lanche result = lancheRepository.save(lanche);
	return ResponseEntity.ok().body(result);
    }
}