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

    private Integer desconto;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "ID_INGREDIENTE", updatable = true, insertable = true)
    private Long idIngrediente;

    /**
     * Este mapeamento foi feito para facilitar o cálculo do desconto nos ingredientes.
     * Como o objeto já está em memória evitamos ter que filtrar a lista de ingredientes várias vezes.
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Ingrediente.class)
    @JoinColumn(name = "ID_INGREDIENTE", insertable = false, updatable = false)
    private Ingrediente ingrediente;

    @Enumerated(value = EnumType.STRING)
    private Operador operador;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PROMOCAO")
    private Promocao promocao;

    private Integer quantidade;

    public Integer getDesconto() {
	return desconto;
    }

    @Override public Long getId() {
	return id;
    }

    public Long getIdIngrediente() {
	return idIngrediente;
    }

    public Ingrediente getIngrediente() {
	return ingrediente;
    }

    public Operador getOperador() {
	return operador;
    }

    public Promocao getPromocao() {
	return promocao;
    }

    public Integer getQuantidade() {
	return quantidade;
    }

    public void setDesconto(final Integer pDesconto) {
	desconto = pDesconto;
    }

    public void setId(final Long pId) {
	id = pId;
    }

    public void setIdIngrediente(final Long pIdIngrediente) {
	idIngrediente = pIdIngrediente;
    }

    public void setIngrediente(final Ingrediente pIngrediente) {
	ingrediente = pIngrediente;
    }

    public void setOperador(final Operador pOperador) {
	operador = pOperador;
    }

    public void setPromocao(final Promocao pPromocao) {
	promocao = pPromocao;
    }

    public void setQuantidade(final Integer pQuantidade) {
	quantidade = pQuantidade;
    }

    @Override public String toString() {
	return "PromocaoRegras{" +
			"idIngrediente=" + idIngrediente +
			", operador=" + operador +
			", quantidade=" + quantidade +
			'}';
    }
}
