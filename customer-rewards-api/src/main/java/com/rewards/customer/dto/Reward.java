package com.rewards.customer.dto;

import java.util.Map;

/**
 * DTO class used to represent customer reward details.
 * <p>
 * This class contains:
 * <p>
 * Customer id
 * Monthly reward points
 * Total accumulated reward points
 * <p>
 * <p>
 * This object is returned as API response to the client.
 * <p>
 * Example Response:
 * <p>
 * {
 * "custId": "CUST101",
 * "monthlyRewardPoints": {
 * "JANUARY": 120,
 * "FEBRUARY": 90
 * },
 * "totalRewardPoints": 210
 * }
 *
 *
 *
 */
public class Reward {

    private String custId;

    private Map<String, Integer> monthlyRewardPoints;

    private int totalRewardPoints;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Map<String, Integer> getMonthlyRewardPoints() {
        return monthlyRewardPoints;
    }

    public void setMonthlyRewardPoints(Map<String, Integer> monthlyRewardPoints) {
        this.monthlyRewardPoints = monthlyRewardPoints;
    }

    public int getTotalRewardPoints() {
        return totalRewardPoints;
    }

    public void setTotalRewardPoints(int totalRewardPoints) {
        this.totalRewardPoints = totalRewardPoints;
    }

    @Override
    public String toString() {
        return "Reward [custId=" + custId + ", monthlyRewardPoints=" + monthlyRewardPoints + ", totalRewardPoints="
                + totalRewardPoints + "]";
    }


}