package com.misterburguer.domain;

import javax.persistence.*;
import java.util.List;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
@Entity
public class Lanche {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(name = "ingredientes_do_lanche",
		    joinColumns = @JoinColumn(name = "id_lanche"),
		    inverseJoinColumns = @JoinColumn(name = "id_ingrediente"))
    private List<Ingrediente> ingredientes;

    private String nome;

    public Long getId() {
	return id;
    }

    public List<Ingrediente> getIngredientes() {
	return ingredientes;
    }

    public String getNome() {
	return nome;
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
}
