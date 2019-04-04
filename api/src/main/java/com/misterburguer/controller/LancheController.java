package com.misterburguer.controller;

import com.misterburguer.application.service.LancheService;
import com.misterburguer.domain.Lanche;
import com.misterburguer.infra.dto.LancheDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lanche")
class LancheController extends BaseResource<Lanche, Long, LancheDTO> {

    LancheController(final LancheService service) {
	super(service, Lanche.class, LancheDTO.class);
    }
}