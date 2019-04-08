package com.misterburguer.infra.util;

import java.math.BigDecimal;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 08/04/19
 */
public class BigDecimalUtil {

    public static BigDecimal setDefaultScale(final BigDecimal pValue) {
	return pValue.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
