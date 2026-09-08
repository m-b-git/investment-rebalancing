package org.investmentrebalancing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InvestmentRebalancerTest {

    private InvestmentRebalancer rebalancer;

    @BeforeEach
    void setUp() {
        rebalancer = new InvestmentRebalancer(new BigDecimal("100000.00"));
    }

    // BUY: when the target percentage is higher than the current percentage, the security should be bought
    // and the number of shares should be calculated correctly.
    @Test
    void shouldCreateBuyOperationWhenTargetPercentageIsHigherThanCurrent() {

        rebalancer.addPosition(
                Security.IBM,
                new Position(20, 10)
        );

        rebalancer.analyzePositions();

        Map<Security, Operation> operations =
                rebalancer.executeOperations();

        Operation operation = operations.get(Security.IBM);

        assertNotNull(operation);
        assertEquals(Security.IBM, operation.getSecurity());
        assertEquals(OperationType.BUY, operation.getType());
        assertEquals(new BigDecimal("66.67"), operation.getShares());
    }

    // SELL: when the target percentage is lower than the current percentage, the security should be sold
    // and the number of shares should be calculated correctly.
    @Test
    void shouldCreateSellOperationWhenTargetPercentageIsLowerThanCurrent() {

        rebalancer.addPosition(
                Security.ORCL,
                new Position(20, 30)
        );

        rebalancer.analyzePositions();

        Map<Security, Operation> operations =
                rebalancer.executeOperations();

        Operation operation = operations.get(Security.ORCL);

        assertNotNull(operation);
        assertEquals(Security.ORCL, operation.getSecurity());
        assertEquals(OperationType.SELL, operation.getType());
        assertEquals(new BigDecimal("45.45"), operation.getShares());
    }

    // BALANCED: when the target and current percentages are equal, no buy or sell operation should be created.
    @Test
    void shouldNotCreateOperationWhenTargetPercentageEqualsCurrent() {

        rebalancer.addPosition(
                Security.MSFT,
                new Position(20, 20)
        );

        rebalancer.analyzePositions();

        Map<Security, Operation> operations =
                rebalancer.executeOperations();

        assertFalse(operations.containsKey(Security.MSFT));
    }

    // PORTFOLIO VALUE: the number of shares should depend on the total portfolio value and should remain correct
    // when a different total asset value is used.
    @Test
    void shouldCalculateSharesUsingDifferentPortfolioValue() {

        rebalancer = new InvestmentRebalancer(new BigDecimal("50000.00"));

        rebalancer.addPosition(
                Security.IBM,
                new Position(30, 20)
        );

        rebalancer.analyzePositions();

        Map<Security, Operation> operations =
                rebalancer.executeOperations();

        Operation operation = operations.get(Security.IBM);

        assertNotNull(operation);
        assertEquals(OperationType.BUY, operation.getType());
        assertEquals(new BigDecimal("33.33"), operation.getShares());
    }

    // ROUNDING: the calculated number of shares should be rounded to two decimal places using the defined HALF_UP rounding rule.
    @Test
    void shouldRoundSharesToTwoDecimalPlaces() {

        rebalancer.addPosition(
                Security.IBM,
                new Position(17, 10)
        );

        rebalancer.analyzePositions();

        Map<Security, Operation> operations =
                rebalancer.executeOperations();

        Operation operation = operations.get(Security.IBM);

        assertNotNull(operation);
        assertEquals(new BigDecimal("46.67"), operation.getShares());
    }

    // MULTIPLE POSITIONS: the portfolio should correctly handle BUY, SELL and balanced positions at the same time,
    // including the calculated number of shares for each operation.
    @Test
    void shouldCalculateOperationsForMultiplePositions() {

        rebalancer.addPosition(
                Security.IBM,
                new Position(20, 10)
        );

        rebalancer.addPosition(
                Security.ORCL,
                new Position(20, 30)
        );

        rebalancer.addPosition(
                Security.MSFT,
                new Position(20, 20)
        );

        rebalancer.analyzePositions();

        Map<Security, Operation> operations =
                rebalancer.executeOperations();

        assertEquals(2, operations.size());

        assertEquals(
                OperationType.BUY,
                operations.get(Security.IBM).getType()
        );

        assertEquals(
                new BigDecimal("66.67"),
                operations.get(Security.IBM).getShares()
        );

        assertEquals(
                OperationType.SELL,
                operations.get(Security.ORCL).getType()
        );

        assertEquals(
                new BigDecimal("45.45"),
                operations.get(Security.ORCL).getShares()
        );

        assertFalse(operations.containsKey(Security.MSFT));
    }
}
