class Solution {
    public int maxProfit(int[] prices) {
        // Initialize maximum profit to 0
        int maxProfit = 0;

        // Track the minimum price seen so far (initially the first price)
        int minPrice = prices[0];

        // Iterate through each price in the array
        for (int currentPrice : prices) {
            // Update maximum profit if selling at current price yields higher profit
            // Profit = current price - minimum price seen so far
            maxProfit = Math.max(maxProfit, currentPrice - minPrice);

            // Update minimum price if current price is lower
            minPrice = Math.min(minPrice, currentPrice);
        }

        // Return the maximum profit achievable
        return maxProfit;
    }
}