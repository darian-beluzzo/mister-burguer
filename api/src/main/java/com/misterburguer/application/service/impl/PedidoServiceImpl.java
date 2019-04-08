package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.IngredienteService;
import com.misterburguer.application.service.PedidoService;
import com.misterburguer.application.service.PromocaoService;
import com.misterburguer.domain.CalculoPedido;
import com.misterburguer.domain.CalculoPedidoItem;
import com.misterburguer.domain.Promocao;
import com.misterburguer.domain.PromocaoRegras;
import com.misterburguer.domain.shared.TipoDesconto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 03/04/19
 */
@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private PromocaoService promocaoService;

    @Override
    @Transactional
    public CalculoPedido calcularPedido(final Map<Long, Integer> pQuantidadeIngredientes) {

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(pQuantidadeIngredientes);
	List<Promocao> promocoes = promocaoService.calcularDescontoPromocao(pQuantidadeIngredientes);

	List<CalculoPedidoItem> itens = new ArrayList<>();

	// Ordeno as promoções pois o desconto no pedido (Ex: 10%) deve ser aplicado por último
	promocoes.sort(Comparator.comparingInt(Promocao::getOrdem));

	for (Promocao promocao : promocoes) {

	    CalculoPedidoItem promocaoRetornoItem = new CalculoPedidoItem();
	    promocaoRetornoItem.setNome(promocao.getNome());
	    itens.add(promocaoRetornoItem);

	    BigDecimal valorDesconto = BigDecimal.ZERO;

	    // Se for desconto no lanche (porcentagem), já calcula direto
	    if (TipoDesconto.isLanche(promocao.getTipoDesconto())) {

		valorDesconto = valorTotal.multiply(promocao.getPorcentagemDesconto())
				.divide(new BigDecimal(100), RoundingMode.HALF_UP);

		promocaoRetornoItem.setQuantidadeDesconto(promocao.getPorcentagemDesconto() + "%");

	    }
	    // Senão calcula o preço de acordo com a configuração de cada ingrediente
	    else if (TipoDesconto.isIngrediente(promocao.getTipoDesconto())) {

		Integer totalDescontar = 0;
		for (PromocaoRegras regra : promocao.getRegras()) {

		    // Se não tem desconto continua para próxima regra pois pode ser apenas uma regra de enquadramento na promoção
		    if (regra.getDesconto() != null && regra.getDesconto() >= 0) {
			Long idIngrediente = regra.getIdIngrediente();
			Integer qtdPedida = pQuantidadeIngredientes.get(idIngrediente);
			// Calcula a quantidade a pagar de acordo com a quantidade e desconto.
			// Assim é mais fácil configurar a regra de desconto. Ex: 2 ganha 1, 3 ganha 1 ou 4 ganha 2, etc
			int qtdDescontar = ((qtdPedida / regra.getQuantidade()) * regra.getDesconto());
			totalDescontar += qtdDescontar;

			BigDecimal valorDescontoItem = regra.getIngrediente().getValor().multiply(new BigDecimal(qtdDescontar));
			valorDesconto = valorDesconto.add(valorDescontoItem);
		    }
		}
		promocaoRetornoItem.setQuantidadeDesconto(totalDescontar.toString());
	    }

	    promocaoRetornoItem.setValorDesconto(valorDesconto);
	    valorTotal = valorTotal.subtract(valorDesconto);
	}

	CalculoPedido calculoPedido = new CalculoPedido(valorTotal, itens);

	return calculoPedido;
    }
}
