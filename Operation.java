package org.investmentrebalancing;

import java.math.BigDecimal;

public class Operation {

    private final Security security;
    private final OperationType type;
    private final BigDecimal shares;

    public Operation(
            Security security,
            OperationType type,
            BigDecimal shares) {

        this.security = security;
        this.type = type;
        this.shares = shares;
    }

    public Security getSecurity() {
        return security;
    }

    public OperationType getType() {
        return type;
    }

    public BigDecimal getShares() {
        return shares;
    }
}
