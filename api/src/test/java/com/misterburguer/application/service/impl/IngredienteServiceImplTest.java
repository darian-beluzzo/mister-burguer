package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.IngredienteService;
import com.misterburguer.domain.Ingrediente;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredienteServiceImplTest {

    @Autowired
    private IngredienteService ingredienteService;

    //	1, Alface            , 0.40
    //	2, Bacon             , 2.00
    //	3, Hamburger de carne, 3.00
    //	4, Ovo               , 0.80
    //	5, Queijo            , 1.50

    @Test
    public void sumarizarIngredientesList_ingredienteInexistenteOuNull() {

	Ingrediente ing1 = new Ingrediente();
	ing1.setId(0L);

	Ingrediente ing2 = new Ingrediente();
	ing2.setId(null);

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(Arrays.asList(ing1, ing2, null));

	Assert.assertEquals(BigDecimal.ZERO, valorTotal);
    }

    @Test
    public void sumarizarIngredientesList_ingredientesNull() {
	List<Ingrediente> ingredientes = null;

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(ingredientes);
	Assert.assertEquals(BigDecimal.ZERO, valorTotal);
    }

    @Test
    public void sumarizarIngredientesList_ingredientesVazio() {
	List<Ingrediente> ingredientes = new ArrayList<>();

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(ingredientes);
	Assert.assertEquals(BigDecimal.ZERO, valorTotal);
    }

    @Test
    public void sumarizarIngredientesList_sucesso() {

	Ingrediente ing1 = new Ingrediente();
	ing1.setId(1L);

	Ingrediente ing2 = new Ingrediente();
	ing2.setId(3L);

	Ingrediente ing3 = new Ingrediente();
	ing3.setId(5L);

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(Arrays.asList(ing1, ing2, ing3));

	Assert.assertEquals(new BigDecimal("4.90"), valorTotal);
    }

    @Test
    public void sumarizarIngredientesMap_ingredienteInexistenteOuNull() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(0L, 2);
	    put(null, 2);
	}};

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(pedido);

	Assert.assertEquals(BigDecimal.ZERO, valorTotal);
    }

    @Test
    public void sumarizarIngredientesMap_pedidoNull() {
	Map<Long, Integer> pedido = null;

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(pedido);
	Assert.assertEquals(BigDecimal.ZERO, valorTotal);
    }

    @Test
    public void sumarizarIngredientesMap_pedidoVazio() {
	Map<Long, Integer> pedido = new HashMap<>();

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(pedido);
	Assert.assertEquals(BigDecimal.ZERO, valorTotal);
    }

    @Test
    public void sumarizarIngredientesMap_quantidadeZeradaOuNull() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(1L, 0);
	    put(2L, null);
	    put(3L, -10);
	    put(4L, 1);
	}};

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(pedido);

	Assert.assertEquals(new BigDecimal("0.80"), valorTotal);
    }

    @Test
    public void sumarizarIngredientesMap_sucesso() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(1L, 1); // alface    0.40
	    put(3L, 2); // hamburger 6.00
	    put(5L, 2); // queijo    3.00
	}};

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(pedido);

	Assert.assertEquals(new BigDecimal("9.40"), valorTotal);
    }
}