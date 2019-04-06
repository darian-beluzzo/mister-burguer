package com.misterburguer.infra.dto;

import com.misterburguer.domain.shared.IObjetoComId;

import java.util.List;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 02/04/19
 */
public class LancheDTO implements IObjetoComId {

    private String descricaoIngredientes;

    private Long id;

    private List<IngredienteDTO> ingredientes;

    private String nome;

    private String urlImagem;

    private String valor;

    public String getDescricaoIngredientes() {
	return descricaoIngredientes;
    }

    public Long getId() {
	return id;
    }

    public List<IngredienteDTO> getIngredientes() {
	return ingredientes;
    }

    public String getNome() {
	return nome;
    }

    public String getUrlImagem() {
	return urlImagem;
    }

    public String getValor() {
	return valor;
    }

    public void setDescricaoIngredientes(final String pDescricaoIngredientes) {
	descricaoIngredientes = pDescricaoIngredientes;
    }

    public void setId(final Long pId) {
	id = pId;
    }

    public void setIngredientes(final List<IngredienteDTO> pIngredientes) {
	ingredientes = pIngredientes;
    }

    public void setNome(final String pNome) {
	nome = pNome;
    }

    public void setUrlImagem(final String pUrlImagem) {
	urlImagem = pUrlImagem;
    }

    public void setValor(final String pValor) {
	valor = pValor;
    }
}
