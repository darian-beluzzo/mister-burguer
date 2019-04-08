package com.misterburguer;

import com.misterburguer.application.service.PromocaoService;
import com.misterburguer.domain.Promocao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = ApplicationConfig.class)
@SpringBootTest
//@DataJpaTest
public class PromocaoServiceImplTest {

    @Autowired
    private PromocaoService promocaoService;

    //	1, Alface
    //	2, Bacon
    //	3, Hamburger
    //	4, Ovo
    //	5, Queijo

    @Test
    public void calcularPromocao_Light_Corretamente() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(1L, 1); // alface
	    put(3L, 1); // hamburger
	    put(5L, 1); // queijo
	}};

	List<Promocao> promocoes = promocaoService.calcularDescontoPromocao(pedido);

	Assert.assertEquals(1, promocoes.size());
	Assert.assertEquals("Light", promocoes.get(0).getNome());
    }

    @Test
    public void calcularPromocao_Light_NaoEnquadra() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(4L, 1); // ovo
	    put(3L, 1); // hamburger
	    put(5L, 1); // queijo
	}};

	List<Promocao> promocoes = promocaoService.calcularDescontoPromocao(pedido);

	Assert.assertEquals(0, promocoes.size());
    }

    @Test
    public void calcularPromocao_Light_TresQueijos_TresCarnes_MultiplasVezes_Corretamente() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(1L, 1); // alface
	    put(3L, 10); // hamburger
	    put(5L, 10); // queijo
	}};

	List<Promocao> promocoes = promocaoService.calcularDescontoPromocao(pedido);

	Assert.assertEquals(3, promocoes.size());
	Assert.assertNotNull(promocoes.stream().filter(i -> "Light".equals(i.getNome())).findFirst().get());
	Assert.assertNotNull(promocoes.stream().filter(i -> "Muita carne".equals(i.getNome())).findFirst().get());
	Assert.assertNotNull(promocoes.stream().filter(i -> "Muito queijo".equals(i.getNome())).findFirst().get());
    }

    @Test
    public void calcularPromocao_TresCarnes_Corretamente() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(2L, 1); // bacon
	    put(3L, 3); // hamburger
	    put(5L, 1); // queijo
	}};

	List<Promocao> promocoes = promocaoService.calcularDescontoPromocao(pedido);

	Assert.assertEquals(1, promocoes.size());
	Promocao promocao = promocoes.get(0);
	Assert.assertEquals("Muita carne", promocao.getNome());
    }

    @Test
    public void calcularPromocao_TresCarnes_MultiplasVezes_Corretamente() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(2L, 1); // bacon
	    put(3L, 10); // hamburger
	    put(5L, 1); // queijo
	}};

	List<Promocao> promocoes = promocaoService.calcularDescontoPromocao(pedido);

	Assert.assertEquals(1, promocoes.size());
	Promocao promocao = promocoes.get(0);
	Assert.assertEquals("Muita carne", promocao.getNome());
    }

    @Test
    public void calcularPromocao_TresCarnes_NaoEnquadra() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(2L, 1); // bacon
	    put(3L, 2); // hamburger
	    put(5L, 1); // queijo
	}};

	List<Promocao> promocoes = promocaoService.calcularDescontoPromocao(pedido);

	Assert.assertEquals(0, promocoes.size());
    }

    @Test
    public void calcularPromocao_TresQueijos_Corretamente() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(2L, 1); // bacon
	    put(3L, 1); // hamburger
	    put(5L, 3); // queijo
	}};

	List<Promocao> promocoes = promocaoService.calcularDescontoPromocao(pedido);

	Assert.assertEquals(1, promocoes.size());
	Promocao promocao = promocoes.get(0);
	Assert.assertEquals("Muito queijo", promocao.getNome());
    }

    @Test
    public void calcularPromocao_TresQueijos_MultiplasVezes_Corretamente() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(2L, 1); // bacon
	    put(3L, 1); // hamburger
	    put(5L, 10); // queijo
	}};

	List<Promocao> promocoes = promocaoService.calcularDescontoPromocao(pedido);

	Assert.assertEquals(1, promocoes.size());
	Promocao promocao = promocoes.get(0);
	Assert.assertEquals("Muito queijo", promocao.getNome());
    }

    @Test
    public void calcularPromocao_TresQueijos_NaoEnquadra() {
	Map<Long, Integer> pedido = new HashMap<Long, Integer>() {{
	    put(2L, 1); // bacon
	    put(3L, 1); // hamburger
	    put(5L, 2); // queijo
	}};

	List<Promocao> promocoes = promocaoService.calcularDescontoPromocao(pedido);

	Assert.assertEquals(0, promocoes.size());
    }
}