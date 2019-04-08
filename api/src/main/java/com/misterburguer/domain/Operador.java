package com.misterburguer.domain;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 06/04/19
 */
public enum Operador {

    TEM,
    NAO_TEM;

    public static boolean isNaoTem(final Operador pOperador) {
	return NAO_TEM.equals(pOperador);
    }

    public static boolean isTem(final Operador pOperador) {
	return TEM.equals(pOperador);
    }
}
