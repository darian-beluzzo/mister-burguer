package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.PromocaoService;
import com.misterburguer.domain.Ingrediente;
import com.misterburguer.domain.Promocao;
import com.misterburguer.domain.PromocaoRegras;
import com.misterburguer.domain.repository.PromocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 03/04/19
 */
@Service
public class PromocaoServiceImpl implements PromocaoService {

    @Autowired
    private PromocaoRepository repository;

    @Override
    public void calcularDescontoPromocao(final List<Ingrediente> pIngredientes,
		    final Map<Long, Integer> pQuantidadeIngredientes) {

	List<Promocao> promocoes = repository.findAll();
	for (Promocao promocao : promocoes) {
	    System.out.println(promocao.getNome());
	    List<PromocaoRegras> regras = promocao.getRegras();
	    for (PromocaoRegras regra : regras) {
		System.out.println(regra.getOperador());
	    }
	}

    }
}
