package com.misterburguer.domain;

import com.misterburguer.domain.shared.IObjetoComId;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
@Entity
public class Lanche implements IObjetoComId {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(name = "ingredientes_do_lanche",
		    joinColumns = @JoinColumn(name = "id_lanche"),
		    inverseJoinColumns = @JoinColumn(name = "id_ingrediente"))
    private List<Ingrediente> ingredientes;

    private String nome;

    private String urlImagem;

    @Transient
    private BigDecimal valor;

    public String getDescricaoIngredientes() {
	if (!CollectionUtils.isEmpty(ingredientes)) {
	    return ingredientes.stream().map(Ingrediente::getNome).collect(Collectors.joining(", "));
	}
	return null;
    }

    public Long getId() {
	return id;
    }

    public List<Ingrediente> getIngredientes() {
	return ingredientes;
    }

    public String getNome() {
	return nome;
    }

    public String getUrlImagem() {
	return urlImagem;
    }

    public BigDecimal getValor() {
	return valor;
    }

    public void setId(final Long pId) {
	id = pId;
    }

    public void setIngredientes(final List<Ingrediente> pIngredientes) {
	ingredientes = pIngredientes;
    }

    public void setNome(final String pNome) {
	nome = pNome;
    }

    public void setUrlImagem(final String pUrlImagem) {
	urlImagem = pUrlImagem;
    }

    public void setValor(final BigDecimal pValor) {
	valor = pValor;
    }
}
