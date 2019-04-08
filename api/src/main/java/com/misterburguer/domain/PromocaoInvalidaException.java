package com.misterburguer.domain;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 06/04/19
 */
public class PromocaoInvalidaException extends Exception {

    public PromocaoInvalidaException(final String pMessage) {
	super(pMessage);
    }
}
