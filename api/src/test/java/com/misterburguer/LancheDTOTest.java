package com.misterburguer;

import com.misterburguer.domain.Ingrediente;
import com.misterburguer.domain.Lanche;
import com.misterburguer.infra.dto.IngredienteDTO;
import com.misterburguer.infra.dto.LancheDTO;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class LancheDTOTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void whenConvertLancheDTOToLancheEntity_thenCorrect() {

	Long id = 1L;
	String nome = "Lanche XYZ";

	LancheDTO dto = new LancheDTO();
	dto.setId(id);
	dto.setNome(nome);
	dto.setValor("10,25");

	Lanche lanche = modelMapper.map(dto, Lanche.class);
	Assert.assertEquals(id, lanche.getId());
	Assert.assertEquals(nome, lanche.getNome());
	//	Assert.assertEquals(new BigDecimal("10.25"), lanche.getValor());

    }

    @Test
    public void whenConvertLancheEntityToLancheDTO_thenCorrect() {

	Ingrediente ingrediente1 = new Ingrediente();
	ingrediente1.setId(1L);
	ingrediente1.setNome("Ingrediente 1");
	ingrediente1.setValor(new BigDecimal("6.25"));

	Ingrediente ingrediente2 = new Ingrediente();
	ingrediente2.setId(2L);
	ingrediente2.setNome("Ingrediente 2");
	ingrediente2.setValor(new BigDecimal("5.95"));

	List<Ingrediente> ingredientes = Arrays.asList(ingrediente1, ingrediente2);

	IngredienteDTO ingredienteDTO1 = new IngredienteDTO();
	ingredienteDTO1.setId(1L);
	ingredienteDTO1.setNome("Ingrediente 1");
	ingredienteDTO1.setValor("6,25");

	IngredienteDTO ingredienteDTO2 = new IngredienteDTO();
	ingredienteDTO2.setId(2L);
	ingredienteDTO2.setNome("Ingrediente 2");
	ingredienteDTO2.setValor("5,95");

	List<IngredienteDTO> ingredientesDTO = Arrays.asList(ingredienteDTO1, ingredienteDTO2);

	Long id = 1L;
	String nome = "Lanche XYZ";
	String urlImagem = "http://www.domain.com/image.png";

	Lanche lanche = new Lanche();
	lanche.setId(id);
	lanche.setNome(nome);
	lanche.setUrlImagem(urlImagem);
	lanche.setIngredientes(ingredientes);

	LancheDTO dto = modelMapper.map(lanche, LancheDTO.class);
	Assert.assertEquals(id, dto.getId());
	Assert.assertEquals(nome, dto.getNome());
	Assert.assertEquals(urlImagem, dto.getUrlImagem());
	Assert.assertThat(dto.getIngredientes(), Matchers.hasItem(ingredienteDTO1));
	Assert.assertThat(dto.getIngredientes(), Matchers.hasItem(ingredienteDTO2));
    }
}