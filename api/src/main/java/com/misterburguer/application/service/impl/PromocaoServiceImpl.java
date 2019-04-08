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

    private boolean enquadraNasRegrasDaPromocao(final List<PromocaoRegras> pRegras,
		    final Map<Long, Integer> pQuantidadeIngredientes) {
	int qtdRegrasOk = 0;

	for (PromocaoRegras regra : pRegras) {

	    Integer qtdPedida = pQuantidadeIngredientes.get(regra.getIdIngrediente());
	    boolean temIngrediente = qtdPedida != null && qtdPedida > 0;

	    if (Operador.isTem(regra.getOperador()) && temIngrediente) {

		boolean temQuantidade = regra.getQuantidade() != null && qtdPedida >= regra.getQuantidade();
		qtdRegrasOk = temQuantidade ? ++qtdRegrasOk : qtdRegrasOk;

	    } else if (Operador.isNaoTem(regra.getOperador())) {
		qtdRegrasOk = !temIngrediente ? ++qtdRegrasOk : qtdRegrasOk;
	    }
	}
	return pRegras.size() == qtdRegrasOk;
    }
}
