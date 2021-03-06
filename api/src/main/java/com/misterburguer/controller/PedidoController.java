package com.misterburguer.controller;

import com.misterburguer.application.service.PedidoService;
import com.misterburguer.domain.CalculoPedido;
import com.misterburguer.infra.dto.CalculoPedidoDTO;
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

    @Autowired
    protected ModelMapper modelMapper;

    public PedidoController(PedidoService pedidoService) {
	this.pedidoService = pedidoService;
    }

    @PostMapping("/calcular")
    ResponseEntity<CalculoPedidoDTO> calcularPedido(@Valid @RequestBody Map<Long, Integer> pedidoMap) {
	log.info("Request to calcular pedido: {}", pedidoMap);

	CalculoPedido calculoPedido = pedidoService.calcularPedido(pedidoMap);

	CalculoPedidoDTO calculoPedidoDTO = modelMapper.map(calculoPedido, CalculoPedidoDTO.class);

	return ResponseEntity.ok().body(calculoPedidoDTO);
    }
}
