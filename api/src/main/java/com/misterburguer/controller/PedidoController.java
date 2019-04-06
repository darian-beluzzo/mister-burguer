package com.misterburguer.controller;

import com.misterburguer.application.service.PedidoService;
import com.misterburguer.infra.dto.PedidoResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 02/04/19
 */
@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    private final Logger log = LoggerFactory.getLogger(com.misterburguer.controller.PedidoController.class);

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
	this.pedidoService = pedidoService;
    }

    @PostMapping("/calcular")
    ResponseEntity<PedidoResponseDTO> calcularPedido(@Valid @RequestBody Map<Long, Integer> pedidoMap)
		    throws URISyntaxException {
	log.info("Request to calcular pedido: {}", pedidoMap);

	pedidoService.calcularPedido(pedidoMap);

	PedidoResponseDTO pedidoResponseDTO = new PedidoResponseDTO();
	pedidoResponseDTO.setValor("12,50");
	pedidoResponseDTO.setPromocao("Desconto promoção: Muita carne");
	pedidoResponseDTO.setDesconto("2,50");
	return ResponseEntity.ok().body(pedidoResponseDTO);
    }
    //
    //    @GetMapping("/pedido/{id}")
    //    ResponseEntity<?> getPedido(@PathVariable Long id) {
    //	Optional<Pedido> pedido = pedidoRepository.findById(id);
    //	return pedido.map(response -> ResponseEntity.ok().body(response))
    //			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    //    }
    //
    //    @GetMapping("/pedido")
    //    Collection<PedidoDTO> pedido() {
    //	//        return pedidoRepository.findAll();
    //	List<Pedido> all = lancheRepository.findAll();
    //	BigDecimal valor;
    //	for (Pedido pedido : all) {
    //	    valor = BigDecimal.ZERO;
    //	    List<Ingrediente> ingredientes = pedido.getIngredientes();
    //	    for (Ingrediente ingrediente : ingredientes) {
    //		valor = valor.add(ingrediente.getValor());
    //	    }
    //	    pedido.setValor(valor);
    //	}
    //	return all;
    //    }

    //    @PutMapping("/pedido")
    //    ResponseEntity<Pedido> updatePedido(@Valid @RequestBody Pedido pedido) {
    //	log.info("Request to update pedido: {}", pedido);
    //	Pedido result = pedidoRepository.save(pedido);
    //	return ResponseEntity.ok().body(result);
    //    }
}
