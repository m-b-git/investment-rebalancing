# Investment Rebalancing

A Java application that calculates BUY and SELL operations required to rebalance an investment portfolio based on target and current security percentages.

## Features

- Compares target and current percentages for each security.
- Determines whether a security should be bought or sold.
- Calculates the transaction value based on the total portfolio assets.
- Calculates the required number of shares using the security price.
- Rounds the calculated number of shares to two decimal places using HALF_UP rounding.
- Handles multiple securities within a portfolio.

## Automated Tests

The project includes automated tests covering:

- BUY operation
- SELL operation
- Balanced position with no operation
- Different portfolio values
- Rounding of calculated shares
- Multiple positions with BUY, SELL and balanced securities

## Manual Tests

Manual test cases are documented in ManualTests.java and cover the main portfolio rebalancing scenarios.

## Technologies

- Java
- JUnit 5
- BigDecimal
