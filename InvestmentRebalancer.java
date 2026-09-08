package org.investmentrebalancing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class InvestmentRebalancer {

    private final Map<Security, Position> positions = new HashMap<>();
    private final Map<Security, OperationType> operations = new HashMap<>();

    private final BigDecimal totalAssets;

    public InvestmentRebalancer(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }

    public void addPosition(Security security, Position position) {
        positions.put(security, position);
    }

    public void analyzePositions() {

        for (Map.Entry<Security, Position> entry : positions.entrySet()) {

            Security security = entry.getKey();
            Position position = entry.getValue();

            int comparison = position.getTargetPercentage()
                    .compareTo(position.getCurrentPercentage());

            if (comparison > 0) {
                operations.put(security, OperationType.BUY);

            } else if (comparison < 0) {
                operations.put(security, OperationType.SELL);
            }
        }
    }

    public Map<Security, Operation> executeOperations() {

        Map<Security, Operation> calculatedOperations = new HashMap<>();
        // SELL operations must be executed before BUY operations to release funds needed for subsequent purchases
        calculateOperations(calculatedOperations, OperationType.SELL);
        calculateOperations(calculatedOperations, OperationType.BUY);

        return calculatedOperations;
    }

    private void calculateOperations(
            Map<Security, Operation> calculatedOperations,
            OperationType operationType) {

        for (Map.Entry<Security, OperationType> entry : operations.entrySet()) {

            if (entry.getValue() != operationType) {
                continue;
            }

            Security security = entry.getKey();
            Position position = positions.get(security);

            BigDecimal variance = position.getTargetPercentage()
                    .subtract(position.getCurrentPercentage())
                    .abs();

            BigDecimal transactionValue = totalAssets
                    .multiply(variance)
                    .divide(BigDecimal.valueOf(100));

            BigDecimal shares = transactionValue
                    .divide(security.getUnitPrice(), 2, RoundingMode.HALF_UP);

            calculatedOperations.put(
                    security,
                    new Operation(
                            security,
                            operationType,
                            shares
                    )
            );
        }
    }

    // PRINTS FOR PRESENTATION
    public void printOperations(Map<Security, Operation> operations) {

        operations.forEach((security, operation) ->
                System.out.println(
                        operation.getType()
                                + ": " + security
                                + " | shares: " + operation.getShares()
                )
        );
    }

    public void printPositions() {

        positions.forEach((security, position) ->
                System.out.println(
                        security
                                + " | target: "
                                + position.getTargetPercentage()
                                + "% | current: "
                                + position.getCurrentPercentage()
                                + "%"
                )
        );
    }

    // MAIN
    public static void main(String[] args) {

        InvestmentRebalancer rebalancer =
                new InvestmentRebalancer(
                        new BigDecimal("100000.00")
                );

        rebalancer.addPosition(Security.IBM, new Position(20, 10));
        rebalancer.addPosition(Security.MSFT, new Position(20, 20));
        rebalancer.addPosition(Security.ORCL, new Position(20, 30));
        rebalancer.addPosition(Security.AAPL, new Position(20, 20));
        rebalancer.addPosition(Security.HD, new Position(20, 20));

        rebalancer.printPositions();

        rebalancer.analyzePositions();

        Map<Security, Operation> operations =
                rebalancer.executeOperations();

        rebalancer.printOperations(operations);
    }
}

// TODO:
// Update currentPercentage after executing buy/sell operations and verify that the variance for each position is 0.
// Optional extension:
// Add an isBalanced() method and a unit test to verify that all positions reach their target percentages.
