package com.misterburguer.infra.dto;

import com.misterburguer.domain.shared.IObjetoComId;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 02/04/19
 */
public class IngredienteDTO implements IObjetoComId {

    private Long id;

    private String nome;

    private String valor;

    public Long getId() {
	return id;
    }

    public String getNome() {
	return nome;
    }

    public String getValor() {
	return valor;
    }

    public void setId(final Long pId) {
	id = pId;
    }

    public void setNome(final String pNome) {
	nome = pNome;
    }

    public void setValor(final String pValor) {
	valor = pValor;
    }
}
