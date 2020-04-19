package com.shu.leetcode.dynamic;


// 股票买卖最大利润问题
public class MaxProfit {


    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     *
     * 注意你不能在买入股票前卖出股票。
     */

    /**
     * 自己的理解：一开始的想法 就是 两层for循环遍历，做差 然后更新最大利润
     *
     * 好的解法：上面的解法是O N方的复杂度；但是我们需要想 我们一次遍历究竟能干那些事 把这些事情搞清楚了 我们自然代码就出来了。
     * 如果没有条件，我们可以先想办法创造先决条件，然后进行正常思路。
     * 这里我们需要理解和寻找 我们一次遍历 能做哪些事情 只要把这件事情想清楚了 问题就能迎刃而解
     *
     * 这道题里面 我们首先一次遍历 可以拿到遍历过的最小值 我们利用当前值减去最小值，就可以拿到最大利润
     */
    private int getMaxProfit(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }


        int minNum = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (minNum != Integer.MAX_VALUE && nums[i] - minNum > maxProfit) {
                maxProfit = nums[i] - minNum;
            }

            if (nums[i] < minNum) {
                minNum = nums[i];
            }
        }

        return maxProfit;
    }

    /**
     * 这次改成股票可以买卖多次, 但是你必须要在出售股票之前把持有的股票卖掉
     *
     * 思路后一天减前一天 只要值大于0 就可以利润累加
     */
    private int getMaxProfit2(int[] nums) {

        int maxProfit = 0;

        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] - nums[i- 1] > 0) {
                maxProfit += nums[i] - nums[i - 1];
            }
        }

        return maxProfit;
    }
}
