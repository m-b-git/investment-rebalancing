package org.investmentrebalancing;

import java.math.BigDecimal;

public class Position {

    private final BigDecimal targetPercentage;
    private final BigDecimal currentPercentage;

    public Position(int targetPercentage, int currentPercentage) {
        this.targetPercentage = BigDecimal.valueOf(targetPercentage);
        this.currentPercentage = BigDecimal.valueOf(currentPercentage);
    }

    public BigDecimal getTargetPercentage() {
        return targetPercentage;
    }

    public BigDecimal getCurrentPercentage() {
        return currentPercentage;
    }
}
