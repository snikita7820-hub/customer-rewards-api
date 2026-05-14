package com.rewards.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRewardSummary {

    private Long customerId;

    private String customerName;

    private List<MonthlyReward> monthlyRewards;

    private int totalPoints;
}