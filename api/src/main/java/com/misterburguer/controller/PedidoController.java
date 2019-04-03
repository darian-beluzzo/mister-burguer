package com.misterburguer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 02/04/19
 */
@RestController
@RequestMapping("/api")
public class PedidoController {

    //    private final Logger log = LoggerFactory.getLogger(com.misterburguer.controller.PedidoController.class);
    //
    //    private PedidoRepository pedidoRepository;
    //
    //    public PedidoController(PedidoRepository pedidoRepository) {
    //	this.pedidoRepository = pedidoRepository;
    //    }
    //
    //    @DeleteMapping("/pedido/{id}")
    //    public ResponseEntity<?> deletePedido(@PathVariable Long id) {
    //	log.info("Request to delete pedido: {}", id);
    //	pedidoRepository.deleteById(id);
    //	return ResponseEntity.ok().build();
    //    }

    //    @PostMapping("/pedido")
//    ResponseEntity<PedidoDTO> createPedido(@Valid @RequestBody Pedido pedido) throws URISyntaxException {
//	log.info("Request to create pedido: {}", pedido);
//	Pedido result = pedidoRepository.save(pedido);
//	return ResponseEntity.created(new URI("/api/pedido/" + result.getId()))
//			.body(result);
//    }
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
