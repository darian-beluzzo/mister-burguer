package com.misterburguer.domain;

import com.misterburguer.domain.shared.IObjetoComId;
import com.misterburguer.domain.shared.TipoDesconto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
@Entity
public class Promocao implements IObjetoComId {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private int ordem;

    private BigDecimal porcentagemDesconto;

    @Transient
    private int qtdDescontar;

    @OneToMany(targetEntity = PromocaoRegras.class, mappedBy = "promocao", orphanRemoval = true)
    private List<PromocaoRegras> regras;

    @Enumerated(value = EnumType.STRING)
    private TipoDesconto tipoDesconto;

    @Override public Long getId() {
	return id;
    }

    public String getNome() {
	return nome;
    }

    public int getOrdem() {
	return ordem;
    }

    public BigDecimal getPorcentagemDesconto() {
	return porcentagemDesconto;
    }

    public int getQtdDescontar() {
	return qtdDescontar;
    }

    public List<PromocaoRegras> getRegras() {
	return regras;
    }

    public TipoDesconto getTipoDesconto() {
	return tipoDesconto;
    }

    public void setId(final Long pId) {
	id = pId;
    }

    public void setNome(final String pNome) {
	nome = pNome;
    }

    public void setOrdem(final int pOrdem) {
	ordem = pOrdem;
    }

    public void setPorcentagemDesconto(final BigDecimal pPorcentagemDesconto) {
	porcentagemDesconto = pPorcentagemDesconto;
    }

    public void setQtdDescontar(final int pQtdDescontar) {
	qtdDescontar = pQtdDescontar;
    }

    public void setRegras(final List<PromocaoRegras> pRegras) {
	regras = pRegras;
    }

    public void setTipoDesconto(final TipoDesconto pTipoDesconto) {
	tipoDesconto = pTipoDesconto;
    }

}
