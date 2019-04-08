package com.misterburguer.domain.shared;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 06/04/19
 */
public enum TipoDesconto {

    LANCHE,
    INGREDIENTE;

    public static boolean isIngrediente(final TipoDesconto pTipoDesconto) {
	return INGREDIENTE.equals(pTipoDesconto);
    }

    public static boolean isLanche(final TipoDesconto pTipoDesconto) {
	return LANCHE.equals(pTipoDesconto);
    }
}
