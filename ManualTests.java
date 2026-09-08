package org.investmentrebalancing;

public class ManualTests {

    /*
     * TC-01 – Rebalance complete portfolio
     *
     * Preconditions:
     * Application is available and portfolio data can be provided.
     *
     * Test data:
     * - Total assets: $100,000
     * - IBM: target 20%, current 10%, price $150
     * - MSFT: target 20%, current 20%, price $90
     * - ORCL: target 20%, current 30%, price $220
     * - AAPL: target 20%, current 20%, price $450
     * - HD: target 20%, current 20%, price $70
     *
     * Steps:
     * 1. Provide the portfolio data.
     * 2. Run the rebalancing process.
     * 3. Review the calculated operations.
     *
     * Expected result:
     * - IBM → BUY → 66.67 shares
     * - ORCL → SELL → 45.45 shares
     * - MSFT → no operation
     * - AAPL → no operation
     * - HD → no operation
     */


    /*
     * TC-02 – Buy security below target percentage
     *
     * Test data:
     * - Total assets: $100,000
     * - IBM target: 20%
     * - IBM current: 10%
     * - IBM price: $150
     *
     * Steps:
     * 1. Provide the IBM position data.
     * 2. Run the rebalancing process.
     * 3. Review the operation.
     *
     * Expected result:
     * - Operation type is BUY.
     * - Number of shares is 66.67.
     */


    /*
     * TC-03 – Sell security above target percentage
     *
     * Test data:
     * - Total assets: $100,000
     * - ORCL target: 20%
     * - ORCL current: 30%
     * - ORCL price: $220
     *
     * Steps:
     * 1. Provide the ORCL position data.
     * 2. Run the rebalancing process.
     * 3. Review the operation.
     *
     * Expected result:
     * - Operation type is SELL.
     * - Number of shares is 45.45.
     */


    /*
     * TC-04 – No operation for balanced position
     *
     * Test data:
     * - Total assets: $100,000
     * - MSFT target: 20%
     * - MSFT current: 20%
     * - MSFT price: $90
     *
     * Steps:
     * 1. Provide the MSFT position data.
     * 2. Run the rebalancing process.
     * 3. Review the operations.
     *
     * Expected result:
     * - No BUY or SELL operation is created for MSFT.
     */


    /*
     * TC-05 – Rebalancing with different total assets value
     *
     * Test data:
     * - Total assets: $50,000
     * - IBM target: 30%
     * - IBM current: 20%
     * - IBM price: $150
     *
     * Steps:
     * 1. Provide the portfolio and IBM position data.
     * 2. Run the rebalancing process.
     * 3. Review the calculated operation.
     *
     * Expected result:
     * - Operation type is BUY.
     * - Number of shares is 33.33.
     */


    /*
     * TC-06 – Rounding calculated number of shares
     *
     * Test data:
     * - Total assets: $100,000
     * - IBM target: 17%
     * - IBM current: 10%
     * - IBM price: $150
     *
     * Steps:
     * 1. Provide the IBM position data.
     * 2. Run the rebalancing process.
     * 3. Review the calculated number of shares.
     *
     * Expected result:
     * - Operation type is BUY.
     * - Number of shares is 46.67.
     * - The result is rounded to two decimal places.
     */

}
