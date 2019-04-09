package com.misterburguer.infra.dto;

import com.misterburguer.ApplicationConfig;
import com.misterburguer.domain.Ingrediente;
import com.misterburguer.domain.Lanche;
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
public class LancheDTOTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testConverteDTOParaEntity() {

	Long idIngrediente = 2L;
	String nomeIngrediente = "Ingrediente XYZ";
	String valorIngrediente = "5,99";

	IngredienteDTO ingredienteDTO = new IngredienteDTO();
	ingredienteDTO.setId(idIngrediente);
	ingredienteDTO.setNome(nomeIngrediente);
	ingredienteDTO.setValor(valorIngrediente);

	Long idLanche = 1L;
	String nomeLanche = "Lanche XYZ";
	String valorLanche = "10,25";
	String urlImagem = "http://www.domain.com/image.png";

	LancheDTO lancheDTO = new LancheDTO();
	lancheDTO.setId(idLanche);
	lancheDTO.setNome(nomeLanche);
	lancheDTO.setValor(valorLanche);
	lancheDTO.setUrlImagem(urlImagem);
	lancheDTO.setIngredientes(Collections.singletonList(ingredienteDTO));

	Lanche lanche = modelMapper.map(lancheDTO, Lanche.class);

	Assert.assertEquals(idLanche, lanche.getId());
	Assert.assertEquals(nomeLanche, lanche.getNome());
	Assert.assertEquals(urlImagem, lanche.getUrlImagem());
	Assert.assertEquals(new BigDecimal(valorLanche.replace(",", ".")), lanche.getValor());

	List<Ingrediente> ingredientes = lanche.getIngredientes();

	Assert.assertNotNull(ingredientes);

	Ingrediente ingrediente = ingredientes.get(0);

	Assert.assertEquals(idIngrediente, ingrediente.getId());
	Assert.assertEquals(nomeIngrediente, ingrediente.getNome());
	Assert.assertEquals(new BigDecimal(valorIngrediente.replace(",", ".")), ingrediente.getValor());
    }

    @Test
    public void testConverteEntityParaDTO() {

	Long idIngrediente = 2L;
	String nomeIngrediente = "Ingrediente XYZ";
	String valorIngrediente = "5,99";

	Ingrediente ingrediente1 = new Ingrediente();
	ingrediente1.setId(idIngrediente);
	ingrediente1.setNome(nomeIngrediente);
	ingrediente1.setValor(new BigDecimal(valorIngrediente.replace(",", ".")));

	Long idLanche = 1L;
	String nomeLanche = "Lanche XYZ";
	String urlImagem = "http://www.domain.com/image.png";
	String valorLanche = "10,25";

	Lanche lanche = new Lanche();
	lanche.setId(idLanche);
	lanche.setNome(nomeLanche);
	lanche.setUrlImagem(urlImagem);
	lanche.setValor(new BigDecimal(valorLanche.replace(",", ".")));
	lanche.setIngredientes(Collections.singletonList(ingrediente1));

	LancheDTO lancheDto = modelMapper.map(lanche, LancheDTO.class);

	Assert.assertEquals(idLanche, lancheDto.getId());
	Assert.assertEquals(nomeLanche, lancheDto.getNome());
	Assert.assertEquals(urlImagem, lancheDto.getUrlImagem());
	Assert.assertEquals(valorLanche, lancheDto.getValor());

	List<IngredienteDTO> ingredientesDto = lancheDto.getIngredientes();

	Assert.assertNotNull(ingredientesDto);

	IngredienteDTO ingredienteDto = ingredientesDto.get(0);

	Assert.assertEquals(idIngrediente, ingredienteDto.getId());
	Assert.assertEquals(nomeIngrediente, ingredienteDto.getNome());
	Assert.assertEquals(valorIngrediente, ingredienteDto.getValor());

    }
}