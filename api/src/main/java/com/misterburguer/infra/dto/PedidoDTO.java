package com.misterburguer.infra.dto;

import com.misterburguer.domain.shared.IObjetoComId;

import java.io.Serializable;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 02/04/19
 */
public class PedidoDTO implements IObjetoComId {

    private Long idIngrediente;

    private Integer quantidade;

    /**
     * Como o pedido não é gravado ainda, não precisa de id
     *
     * @return o id do objeto
     */
    @Override
    public Serializable getId() {
	return null;
    }

    public Long getIdIngrediente() {
	return idIngrediente;
    }

    public Integer getQuantidade() {
	return quantidade;
    }

    public void setIdIngrediente(final Long pIdIngrediente) {
	idIngrediente = pIdIngrediente;
    }

    public void setQuantidade(final Integer pQuantidade) {
	quantidade = pQuantidade;
    }
}
