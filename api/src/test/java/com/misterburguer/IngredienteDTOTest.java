package com.misterburguer;

import com.misterburguer.domain.Ingrediente;
import com.misterburguer.infra.dto.IngredienteDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class IngredienteDTOTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void whenConvertIngredienteDTOToIngredienteEntity_thenCorrect() {

	Long id = 1L;
	String nome = "Ingrediente XYZ";

	IngredienteDTO dto = new IngredienteDTO();
	dto.setId(id);
	dto.setNome(nome);
	dto.setValor("10,25");

	Ingrediente ingrediente = modelMapper.map(dto, Ingrediente.class);
	Assert.assertEquals(id, ingrediente.getId());
	Assert.assertEquals(nome, ingrediente.getNome());
	Assert.assertEquals(new BigDecimal("10.25"), ingrediente.getValor());

    }

    @Test
    public void whenConvertIngredienteEntityToIngredienteDTO_thenCorrect() {

	Long id = 1L;
	String nome = "Ingrediente XYZ";

	Ingrediente ingrediente = new Ingrediente();
	ingrediente.setId(id);
	ingrediente.setNome(nome);
	ingrediente.setValor(new BigDecimal("10.25"));

	IngredienteDTO dto = modelMapper.map(ingrediente, IngredienteDTO.class);
	Assert.assertEquals(id, dto.getId());
	Assert.assertEquals(nome, dto.getNome());
	Assert.assertEquals("10,25", dto.getValor());
    }
}