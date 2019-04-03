package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.BaseService;
import com.misterburguer.application.service.LancheService;
import com.misterburguer.domain.Lanche;
import com.misterburguer.domain.repository.LancheRepository;
import org.springframework.stereotype.Service;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
@Service
public class LancheServiceImpl extends BaseService<Lanche, Long> implements LancheService {

    public LancheServiceImpl(final LancheRepository repository) {
	super(Lanche.class, repository);
    }
}
