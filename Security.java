package org.investmentrebalancing;

import java.math.BigDecimal;

public enum Security {

    IBM(new BigDecimal("150.00")),
    MSFT(new BigDecimal("90.00")),
    ORCL(new BigDecimal("220.00")),
    AAPL(new BigDecimal("450.00")),
    HD(new BigDecimal("70.00"));

    private final BigDecimal unitPrice;

    Security(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}
