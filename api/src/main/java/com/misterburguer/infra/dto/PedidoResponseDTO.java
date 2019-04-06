package com.misterburguer.infra.dto;

import com.misterburguer.domain.shared.IObjetoComId;

import java.io.Serializable;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 02/04/19
 */
public class PedidoResponseDTO implements IObjetoComId {

    private String desconto;

    private String promocao;

    private String valor;

    public String getDesconto() {
	return desconto;
    }

    /**
     * Como o pedido não é gravado ainda, não precisa de id
     *
     * @return o id do objeto
     */
    @Override
    public Serializable getId() {
	return null;
    }

    public String getPromocao() {
	return promocao;
    }

    public String getValor() {
	return valor;
    }

    public void setDesconto(final String pDesconto) {
	desconto = pDesconto;
    }

    public void setPromocao(final String pPromocao) {
	promocao = pPromocao;
    }

    public void setValor(final String pValor) {
	valor = pValor;
    }
}
