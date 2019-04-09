package com.misterburguer.infra.dto;

import com.misterburguer.ApplicationConfig;
import com.misterburguer.domain.CalculoPedido;
import com.misterburguer.domain.CalculoPedidoItem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class CalculoPedidoDTOTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testConverteDTOParaEntity() {

	String nome = "XYZ";
	String quantidadeDesconto = "4";
	String valorDesconto = "5,99";

	CalculoPedidoItemDTO itemDTO = new CalculoPedidoItemDTO();
	itemDTO.setNome(nome);
	itemDTO.setQuantidadeDesconto(quantidadeDesconto);
	itemDTO.setValorDesconto(valorDesconto);

	String valorCalculoPedido = "10,25";

	CalculoPedidoDTO calculoPedidoDTO = new CalculoPedidoDTO();
	calculoPedidoDTO.setValorTotal(valorCalculoPedido);
	calculoPedidoDTO.setItens(Collections.singletonList(itemDTO));

	CalculoPedido calculoPedido = modelMapper.map(calculoPedidoDTO, CalculoPedido.class);

	Assert.assertEquals(new BigDecimal(valorCalculoPedido.replace(",", ".")), calculoPedido.getValorTotal());

	List<CalculoPedidoItem> itens = calculoPedido.getItens();

	Assert.assertNotNull(itens);

	CalculoPedidoItem item = itens.get(0);

	Assert.assertEquals(nome, item.getNome());
	Assert.assertEquals(quantidadeDesconto, item.getQuantidadeDesconto());
	Assert.assertEquals(new BigDecimal(valorDesconto.replace(",", ".")), item.getValorDesconto());
    }

    @Test
    public void testConverteEntityParaDTO() {

	String nome = "XYZ";
	String quantidadeDesconto = "4";
	String valorDesconto = "5,99";

	CalculoPedidoItem item = new CalculoPedidoItem();
	item.setNome(nome);
	item.setQuantidadeDesconto(quantidadeDesconto);
	item.setValorDesconto(new BigDecimal(valorDesconto.replace(",", ".")));

	String valorCalculoPedido = "10,25";

	CalculoPedido calculoPedido = new CalculoPedido();
	calculoPedido.setValorTotal(new BigDecimal(valorCalculoPedido.replace(",", ".")));
	calculoPedido.setItens(Collections.singletonList(item));

	CalculoPedidoDTO calculoPedidoDTO = modelMapper.map(calculoPedido, CalculoPedidoDTO.class);

	Assert.assertEquals(valorCalculoPedido, calculoPedidoDTO.getValorTotal());

	List<CalculoPedidoItemDTO> itensDTO = calculoPedidoDTO.getItens();

	Assert.assertNotNull(itensDTO);

	CalculoPedidoItemDTO itemDTO = itensDTO.get(0);

	Assert.assertEquals(nome, itemDTO.getNome());
	Assert.assertEquals(quantidadeDesconto, itemDTO.getQuantidadeDesconto());
	Assert.assertEquals(valorDesconto, itemDTO.getValorDesconto());
    }
}