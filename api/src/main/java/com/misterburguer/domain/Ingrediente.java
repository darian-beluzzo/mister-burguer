package com.misterburguer.domain;

import com.misterburguer.domain.shared.IObjetoComId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
@Entity
public class Ingrediente implements IObjetoComId {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal valor;

    public Long getId() {
	return id;
    }

    public String getNome() {
	return nome;
    }

    public BigDecimal getValor() {
	return valor;
    }

    public void setId(final Long pId) {
	id = pId;
    }

    public void setNome(final String pNome) {
	nome = pNome;
    }

    public void setValor(final BigDecimal pValor) {
	valor = pValor;
    }
}
