package com.misterburguer.infra.dto;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
public class CalculoLancheItemDTO {

    private String nome;

    private String quantidadeDesconto;

    private String valorDesconto;

    public String getNome() {
	return nome;
    }

    public String getQuantidadeDesconto() {
	return quantidadeDesconto;
    }

    public String getValorDesconto() {
	return valorDesconto;
    }

    public void setNome(final String pNome) {
	nome = pNome;
    }

    public void setQuantidadeDesconto(final String pQuantidadeDesconto) {
	quantidadeDesconto = pQuantidadeDesconto;
    }

    public void setValorDesconto(final String pValorDesconto) {
	valorDesconto = pValorDesconto;
    }
}
