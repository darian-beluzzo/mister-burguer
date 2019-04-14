package com.misterburguer.infra.dto;

import com.misterburguer.ApplicationConfig;
import com.misterburguer.domain.CalculoLanche;
import com.misterburguer.domain.CalculoPromocao;
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
public class CalculoLancheDTOTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testConverteDTOParaEntity() {

	String nome = "XYZ";
	Integer quantidadeDesconto = 4;
	String valorDesconto = "5,99";

	CalculoLancheItemDTO itemDTO = new CalculoLancheItemDTO();
	itemDTO.setNome(nome);
	itemDTO.setQuantidadeDesconto(quantidadeDesconto.toString());
	itemDTO.setValorDesconto(valorDesconto);

	String valorCalculoLanche = "10,25";

	CalculoLancheDTO calculoLancheDTO = new CalculoLancheDTO();
	calculoLancheDTO.setValorTotal(valorCalculoLanche);
	calculoLancheDTO.setItens(Collections.singletonList(itemDTO));

	CalculoLanche calculoLanche = modelMapper.map(calculoLancheDTO, CalculoLanche.class);

	Assert.assertEquals(new BigDecimal(valorCalculoLanche.replace(",", ".")), calculoLanche.getValorTotal());

	List<CalculoPromocao> itens = calculoLanche.getItens();

	Assert.assertNotNull(itens);

	CalculoPromocao item = itens.get(0);

	Assert.assertEquals(nome, item.getNome());
	Assert.assertEquals(quantidadeDesconto, item.getQuantidadeDesconto());
	Assert.assertEquals(new BigDecimal(valorDesconto.replace(",", ".")), item.getValorDesconto());
    }

    @Test
    public void testConverteEntityParaDTO() {

	String nome = "XYZ";
	Integer quantidadeDesconto = 4;
	String valorDesconto = "5,99";

	CalculoPromocao item = new CalculoPromocao();
	item.setNome(nome);
	item.setQuantidadeDesconto(quantidadeDesconto);
	item.setValorDesconto(new BigDecimal(valorDesconto.replace(",", ".")));

	String valorCalculoLanche = "10,25";

	CalculoLanche calculoLanche = new CalculoLanche();
	calculoLanche.setValorTotal(new BigDecimal(valorCalculoLanche.replace(",", ".")));
	calculoLanche.setItens(Collections.singletonList(item));

	CalculoLancheDTO calculoLancheDTO = modelMapper.map(calculoLanche, CalculoLancheDTO.class);

	Assert.assertEquals(valorCalculoLanche, calculoLancheDTO.getValorTotal());

	List<CalculoLancheItemDTO> itensDTO = calculoLancheDTO.getItens();

	Assert.assertNotNull(itensDTO);

	CalculoLancheItemDTO itemDTO = itensDTO.get(0);

	Assert.assertEquals(nome, itemDTO.getNome());
	Assert.assertEquals(quantidadeDesconto.toString(), itemDTO.getQuantidadeDesconto());
	Assert.assertEquals(valorDesconto, itemDTO.getValorDesconto());
    }
}