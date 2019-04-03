package com.misterburguer.infra.dto;

import java.util.List;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 02/04/19
 */
public class LancheDTO {

    private Long id;

    private List<IngredienteDTO> ingredientes;

    private String nome;

    private String urlImagem;

    private String valor;

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
