package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.BaseService;
import com.misterburguer.application.service.IngredienteService;
import com.misterburguer.application.service.LancheService;
import com.misterburguer.application.service.PromocaoService;
import com.misterburguer.domain.Lanche;
import com.misterburguer.domain.repository.LancheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
@Service
public class LancheServiceImpl extends BaseService<Lanche, Long> implements LancheService {

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private PromocaoService promocaoService;

    public LancheServiceImpl(final LancheRepository repository) {
	super(Lanche.class, repository);
    }

    @Override
    public List<Lanche> findAll() {
	List<Lanche> lanches = super.findAll();
	lanches.stream().map(this::calcularValorLanche).collect(Collectors.toList());
	return lanches;
    }

    @Override
    public Optional<Lanche> findById(final Long pId) {
	Optional<Lanche> lancheOptional = super.findById(pId);
	lancheOptional.ifPresent(this::calcularValorLanche);
	return lancheOptional;
    }

    @Override
    public Lanche update(final Lanche pEntity) {
	Lanche lanche = super.update(pEntity);
	return calcularValorLanche(lanche);
    }

    public Lanche calcularValorLanche(final Lanche pLanche) {
	BigDecimal valorLanche = ingredienteService.sumarizarValorIngredientes(pLanche.getIngredientes());
	//	BigDecimal valorDesconto = promocaoService.enquadrarPromocao(pLanche.getIngredientes());
	pLanche.setValor(valorLanche);
	return pLanche;
    }
}
