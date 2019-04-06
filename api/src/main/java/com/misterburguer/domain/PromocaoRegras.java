package com.misterburguer.domain;

import com.misterburguer.domain.shared.IObjetoComId;

import javax.persistence.*;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
@Entity
public class PromocaoRegras implements IObjetoComId {

    @Id
    @GeneratedValue
    private Long id;

    private Long idIngrediente;

    private String operador;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PROMOCAO")
    private Promocao promocao;

    @Override public Long getId() {
	return id;
    }

    public Long getIdIngrediente() {
	return idIngrediente;
    }

    public String getOperador() {
	return operador;
    }

    public Promocao getPromocao() {
	return promocao;
    }

    public void setId(final Long pId) {
	id = pId;
    }

    public void setIdIngrediente(final Long pIdIngrediente) {
	idIngrediente = pIdIngrediente;
    }

    public void setOperador(final String pOperador) {
	operador = pOperador;
    }

    public void setPromocao(final Promocao pPromocao) {
	promocao = pPromocao;
    }
}
