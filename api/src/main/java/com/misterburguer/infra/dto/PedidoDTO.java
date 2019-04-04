package com.misterburguer.infra.dto;

import com.misterburguer.domain.shared.IObjetoComId;

import java.io.Serializable;
import java.util.Map;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 02/04/19
 */
public class PedidoDTO implements IObjetoComId {

    private Long idLanche;

    private Map<Long, Integer> ingredientes;

    /**
     * Como o pedido não é gravado ainda, não precisa de id
     *
     * @return o id do objeto
     */
    @Override
    public Serializable getId() {
	return null;
    }
}
