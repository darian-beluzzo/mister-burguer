package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.PedidoService;
import com.misterburguer.domain.CalculoPedido;
import com.misterburguer.domain.CalculoPedidoItem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PedidoServiceImplTest {

    @Autowired
    private PedidoService pedidoService;

    //	1, Alface            , 0.40
    //	2, Bacon             , 2.00
    //	3, Hamburger de carne, 3.00
    //	4, Ovo               , 0.80
    //	5, Queijo            , 1.50

    @Test
    public void calcularPromocao_Light_Corretamente() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(1L, 1); // alface    0.40
	    put(3L, 1); // hamburger 3.00
	    put(5L, 1); // queijo    1.50
	}};

	CalculoPedido calculoPedido = pedidoService.calcularPedido(pedido);

	Assert.assertEquals(new BigDecimal("4.41"), calculoPedido.getValorTotal());

	List<CalculoPedidoItem> itens = calculoPedido.getItens();

	Assert.assertEquals(1, itens.size());

	CalculoPedidoItem light = itens.stream().filter(i -> "Light".equals(i.getNome())).findFirst().get();
	Assert.assertNotNull(light);
	Assert.assertEquals("10.0%", light.getQuantidadeDesconto());
	Assert.assertEquals(new BigDecimal("0.49"), light.getValorDesconto());

    }

    @Test
    public void calcularPromocao_Light_NaoEnquadra() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(4L, 1); // ovo        0.80
	    put(3L, 1); // hamburger  3.00
	    put(5L, 1); // queijo     1.50
	}};

	CalculoPedido calculoPedido = pedidoService.calcularPedido(pedido);

	Assert.assertEquals(new BigDecimal("5.30"), calculoPedido.getValorTotal());

	Assert.assertEquals(0, calculoPedido.getItens().size());
    }

    @Test
    public void calcularPromocao_Light_TresQueijos_TresCarnes_MultiplasVezes_Corretamente() {

	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(1L, 1); // alface    0.40 x 1 = 0.40
	    put(3L, 7); // hamburger 3.00 x 7 = 21.00 => (-2) = 15.00
	    put(5L, 7); // queijo    1.50 x 7 = 10.50 => (-2) = 7.50
	    //             Total sem desconto = 31.90
	    //                   31.90 - 6.00 = 25.90
	    //                   25.90 - 3.00 = 22.90
	    //                   22.90 - 10%  = 20.61
	}};

	CalculoPedido calculoPedido = pedidoService.calcularPedido(pedido);

	Assert.assertEquals(new BigDecimal("20.61"), calculoPedido.getValorTotal());

	List<CalculoPedidoItem> itens = calculoPedido.getItens();

	Assert.assertEquals(3, itens.size());

	CalculoPedidoItem light = itens.stream().filter(i -> "Light".equals(i.getNome())).findFirst().get();
	Assert.assertNotNull(light);
	Assert.assertEquals("10.0%", light.getQuantidadeDesconto());
	Assert.assertEquals(new BigDecimal("2.29"), light.getValorDesconto());

	CalculoPedidoItem muitaCarne = itens.stream().filter(i -> "Muita carne".equals(i.getNome())).findFirst().get();
	Assert.assertNotNull(muitaCarne);
	Assert.assertEquals("2", muitaCarne.getQuantidadeDesconto());
	Assert.assertEquals(new BigDecimal("6.00"), muitaCarne.getValorDesconto());

	CalculoPedidoItem muitoQueijo = itens.stream().filter(i -> "Muito queijo".equals(i.getNome())).findFirst().get();
	Assert.assertNotNull(muitoQueijo);
	Assert.assertEquals("2", muitoQueijo.getQuantidadeDesconto());
	Assert.assertEquals(new BigDecimal("3.00"), muitoQueijo.getValorDesconto());
    }

    @Test
    public void calcularPromocao_TresCarnes_Corretamente() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(2L, 1); // bacon      2.00
	    put(3L, 3); // hamburger  9.00 = (-1) = 6.00
	    put(5L, 1); // queijo     1.50
	    //             Total      9.50
	}};

	CalculoPedido calculoPedido = pedidoService.calcularPedido(pedido);

	Assert.assertEquals(new BigDecimal("9.50"), calculoPedido.getValorTotal());

	List<CalculoPedidoItem> itens = calculoPedido.getItens();

	Assert.assertEquals(1, itens.size());

	CalculoPedidoItem muitaCarne = itens.stream().filter(i -> "Muita carne".equals(i.getNome())).findFirst().get();
	Assert.assertNotNull(muitaCarne);
	Assert.assertEquals("1", muitaCarne.getQuantidadeDesconto());
	Assert.assertEquals(new BigDecimal("3.00"), muitaCarne.getValorDesconto());
    }

    @Test
    public void calcularPromocao_TresCarnes_MultiplasVezes_Corretamente() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(2L, 1);  // bacon      2.00
	    put(3L, 10); // hamburger  30.00 = (-9) = 21.00
	    put(5L, 1);  // queijo     1.50
	    //	            Total      24.50
	}};

	CalculoPedido calculoPedido = pedidoService.calcularPedido(pedido);

	Assert.assertEquals(new BigDecimal("24.50"), calculoPedido.getValorTotal());

	List<CalculoPedidoItem> itens = calculoPedido.getItens();

	Assert.assertEquals(1, itens.size());

	CalculoPedidoItem muitaCarne = itens.stream().filter(i -> "Muita carne".equals(i.getNome())).findFirst().get();

	Assert.assertNotNull(muitaCarne);
	Assert.assertEquals("3", muitaCarne.getQuantidadeDesconto());
	Assert.assertEquals(new BigDecimal("9.00"), muitaCarne.getValorDesconto());
    }

    @Test
    public void calcularPromocao_TresCarnes_NaoEnquadra() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(2L, 1); // bacon      2.00
	    put(3L, 2); // hamburger  6.00
	    put(5L, 1); // queijo     1.50
	    //	           Total      9.50
	}};

	CalculoPedido calculoPedido = pedidoService.calcularPedido(pedido);

	Assert.assertEquals(new BigDecimal("9.50"), calculoPedido.getValorTotal());
	Assert.assertEquals(0, calculoPedido.getItens().size());
    }

    @Test
    public void calcularPromocao_TresQueijos_Corretamente() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(2L, 1); // bacon      2.00
	    put(3L, 1); // hamburger  3.00
	    put(5L, 3); // queijo     4.50 = (-1) = 3.00
	    //	           Total      8.00
	}};

	CalculoPedido calculoPedido = pedidoService.calcularPedido(pedido);

	Assert.assertEquals(new BigDecimal("8.00"), calculoPedido.getValorTotal());

	List<CalculoPedidoItem> itens = calculoPedido.getItens();

	Assert.assertEquals(1, itens.size());

	CalculoPedidoItem muitoQueijo = itens.stream().filter(i -> "Muito queijo".equals(i.getNome())).findFirst().get();
	Assert.assertNotNull(muitoQueijo);
	Assert.assertEquals("1", muitoQueijo.getQuantidadeDesconto());
	Assert.assertEquals(new BigDecimal("1.50"), muitoQueijo.getValorDesconto());
    }

    @Test
    public void calcularPromocao_TresQueijos_MultiplasVezes_Corretamente() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(2L, 1);  // bacon      2.00
	    put(3L, 1);  // hamburger  3.00
	    put(5L, 10); // queijo     15.00 = (-3) = 10.50
	    //	            Total      15.50
	}};

	CalculoPedido calculoPedido = pedidoService.calcularPedido(pedido);

	Assert.assertEquals(new BigDecimal("15.50"), calculoPedido.getValorTotal());

	List<CalculoPedidoItem> itens = calculoPedido.getItens();

	Assert.assertEquals(1, itens.size());

	CalculoPedidoItem muitoQueijo = itens.stream().filter(i -> "Muito queijo".equals(i.getNome())).findFirst().get();
	Assert.assertNotNull(muitoQueijo);
	Assert.assertEquals("3", muitoQueijo.getQuantidadeDesconto());
	Assert.assertEquals(new BigDecimal("4.50"), muitoQueijo.getValorDesconto());
    }

    @Test
    public void calcularPromocao_TresQueijos_NaoEnquadra() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(2L, 1); // bacon      2.00
	    put(3L, 1); // hamburger  3.00
	    put(5L, 2); // queijo     3.00
	    //	           Total      8.00
	}};

	CalculoPedido calculoPedido = pedidoService.calcularPedido(pedido);

	Assert.assertEquals(new BigDecimal("8.00"), calculoPedido.getValorTotal());
	Assert.assertEquals(0, calculoPedido.getItens().size());
    }
}