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
    public void sumarizarIngredientesList_ingredienteSemValorOuNull() {

	Ingrediente ing1 = new Ingrediente();
	ing1.setValor(null);

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(Arrays.asList(ing1, null));

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
	ing1.setValor(new BigDecimal("0.40"));

	Ingrediente ing2 = new Ingrediente();
	ing2.setValor(new BigDecimal("3.00"));

	Ingrediente ing3 = new Ingrediente();
	ing3.setValor(new BigDecimal("1.50"));

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(Arrays.asList(ing1, ing2, ing3));

	Assert.assertEquals(new BigDecimal("4.90"), valorTotal);
    }

    @Test
    public void sumarizarIngredientesMap_ingredienteInexistenteOuNull() {

	Ingrediente ing1 = new Ingrediente();
	ing1.setValor(null);

	Map<Ingrediente, Integer> pedido = new HashMap<Ingrediente, Integer>() {{
	    put(ing1, 2);
	    put(null, 2);
	}};

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(pedido);

	Assert.assertEquals(BigDecimal.ZERO, valorTotal);
    }

    @Test
    public void sumarizarIngredientesMap_pedidoNull() {
	Map<Ingrediente, Integer> pedido = null;

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(pedido);
	Assert.assertEquals(BigDecimal.ZERO, valorTotal);
    }

    @Test
    public void sumarizarIngredientesMap_pedidoVazio() {
	Map<Ingrediente, Integer> pedido = new HashMap<>();

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(pedido);
	Assert.assertEquals(BigDecimal.ZERO, valorTotal);
    }

    @Test
    public void sumarizarIngredientesMap_quantidadeZeradaOuNull() {
	Ingrediente ing1 = new Ingrediente();
	ing1.setValor(new BigDecimal("1.50"));

	Ingrediente ing2 = new Ingrediente();
	ing2.setValor(new BigDecimal("1.50"));

	Ingrediente ing3 = new Ingrediente();
	ing3.setValor(new BigDecimal("1.50"));

	Ingrediente ing4 = new Ingrediente();
	ing4.setValor(new BigDecimal("1.50"));

	Map<Ingrediente, Integer> pedido = new HashMap<Ingrediente, Integer>() {{
	    put(ing1, 0);
	    put(ing2, null);
	    put(ing3, -10);
	    put(ing4, 1);
	}};

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(pedido);

	Assert.assertEquals(new BigDecimal("1.50"), valorTotal);
    }

    @Test
    public void sumarizarIngredientesMap_sucesso() {

	Ingrediente ing1 = new Ingrediente();
	ing1.setValor(new BigDecimal("0.40"));

	Ingrediente ing2 = new Ingrediente();
	ing2.setValor(new BigDecimal("3.00"));

	Ingrediente ing3 = new Ingrediente();
	ing3.setValor(new BigDecimal("1.50"));

	Map<Ingrediente, Integer> pedido = new HashMap<Ingrediente, Integer>() {{
	    put(ing1, 1); // alface    0.40
	    put(ing2, 2); // hamburger 6.00
	    put(ing3, 2); // queijo    3.00
	}};

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(pedido);

	Assert.assertEquals(new BigDecimal("9.40"), valorTotal);
    }
}