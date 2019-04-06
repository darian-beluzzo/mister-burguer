package com.misterburguer.domain;

import com.misterburguer.domain.shared.IObjetoComId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
@Entity
public class Promocao implements IObjetoComId {

    private Integer desconto;

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private Integer quantidade;

    //    @OneToMany
    //    @JoinTable(name = "promocao_regras", joinColumns = @JoinColumn(name = "id_lanche"))
    @OneToMany(targetEntity = PromocaoRegras.class, mappedBy = "promocao", orphanRemoval = true)
    private List<PromocaoRegras> regras;

    private String unidade;

    public Integer getDesconto() {
	return desconto;
    }

    @Override public Long getId() {
	return id;
    }

    public String getNome() {
	return nome;
    }

    public Integer getQuantidade() {
	return quantidade;
    }

    public List<PromocaoRegras> getRegras() {
	return regras;
    }

    public String getUnidade() {
	return unidade;
    }

    public void setDesconto(final Integer pDesconto) {
	desconto = pDesconto;
    }

    public void setId(final Long pId) {
	id = pId;
    }

    public void setNome(final String pNome) {
	nome = pNome;
    }

    public void setQuantidade(final Integer pQuantidade) {
	quantidade = pQuantidade;
    }

    public void setRegras(final List<PromocaoRegras> pRegras) {
	regras = pRegras;
    }

    public void setUnidade(final String pUnidade) {
	unidade = pUnidade;
    }
}
