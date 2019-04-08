package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.PromocaoService;
import com.misterburguer.domain.Operador;
import com.misterburguer.domain.Promocao;
import com.misterburguer.domain.PromocaoRegras;
import com.misterburguer.domain.repository.PromocaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 03/04/19
 */
@Service
public class PromocaoServiceImpl implements PromocaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PromocaoServiceImpl.class);

    @Autowired
    private PromocaoRepository repository;

    @Override
    @Transactional
    public List<Promocao> calcularDescontoPromocao(final Map<Long, Integer> pQuantidadeIngredientes) {

	final List<Promocao> retorno = new ArrayList<>();

	List<Promocao> promocoes = repository.findAll();
	for (Promocao promocao : promocoes) {

	    List<PromocaoRegras> regras = promocao.getRegras();

	    if (CollectionUtils.isEmpty(regras)) {
		String message = String
				.format("A Promoção '%s' não pôde ser aplicada pois não possui regras.",
						promocao.getNome());
		// Ignora a promoção e continua
		LOGGER.error(message);
	    }

	    if (enquadraNasRegrasDaPromocao(regras, pQuantidadeIngredientes)) {
		retorno.add(promocao);
	    }

	}
	return retorno;
    }

    //    private void enquadraNaPromocaoDeIngrediente(final Map<Long, Integer> pQuantidadeIngredientes,
    //		    final Promocao pPromocao) throws PromocaoInvalidaException {
    //	final double desconto;
    //	int qtdRegrasOk = 0;
    //
    //	List<PromocaoRegras> regras = pPromocao.getRegras();
    //
    //	if (regras.size() > 1) {
    //	    String message = String
    //			    .format("A Promoção '%s' não pôde ser aplicada pois possui mais de uma regra.", pPromocao.getNome());
    //	    throw new PromocaoInvalidaException(message);
    //	}
    //
    //	for (PromocaoRegras regra : regras) {
    //
    //	    boolean temIngrediente = pQuantidadeIngredientes.containsKey(regra.getId());
    //
    //	    if (Operador.isTem(regra.getOperador()) && temIngrediente) {
    //
    //		Integer qtdPedida = pQuantidadeIngredientes.get(regra.getIdIngrediente());
    //		boolean temQuantidade = qtdPedida != null && regra.getQuantidade() != null && qtdPedida > 0
    //				&& qtdPedida >= regra.getQuantidade();
    //		qtdRegrasOk = temQuantidade ? ++qtdRegrasOk : qtdRegrasOk;
    //
    //		//		desconto = regra.getDesconto();
    //	    } else if (Operador.isNaoTem(regra.getOperador())) {
    //		qtdRegrasOk = !temIngrediente ? ++qtdRegrasOk : qtdRegrasOk;
    //	    }
    //	}
    //	if (regras.size() == qtdRegrasOk) {
    //	    //	    pRetorno.add(pPromocao);
    //	}
    //    }

    private boolean enquadraNasRegrasDaPromocao(final List<PromocaoRegras> pRegras,
		    final Map<Long, Integer> pQuantidadeIngredientes) {
	int qtdRegrasOk = 0;

	for (PromocaoRegras regra : pRegras) {

	    boolean temIngrediente = pQuantidadeIngredientes.containsKey(regra.getIdIngrediente());

	    if (Operador.isTem(regra.getOperador()) && temIngrediente) {

		Integer qtdPedida = pQuantidadeIngredientes.get(regra.getIdIngrediente());
		boolean temQuantidade = qtdPedida != null && regra.getQuantidade() != null && qtdPedida > 0
				&& qtdPedida >= regra.getQuantidade();
		qtdRegrasOk = temQuantidade ? ++qtdRegrasOk : qtdRegrasOk;

	    } else if (Operador.isNaoTem(regra.getOperador())) {
		qtdRegrasOk = !temIngrediente ? ++qtdRegrasOk : qtdRegrasOk;
	    }
	}
	return pRegras.size() == qtdRegrasOk;
    }
}
