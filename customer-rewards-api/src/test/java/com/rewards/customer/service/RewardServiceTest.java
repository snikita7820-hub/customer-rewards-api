package com.rewards.customer.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardServiceTest {
    private final RewardService service = new RewardService();

    @Test
    void calculateRewardPoints() {

        int points = service.calculateRewardPoints(120);

        assertEquals(90, points);
    }

    @Test
    void zeroPointsForAmtBelow50() {

        int points = service.calculateRewardPoints(40);

        assertEquals(0, points);
    }
}
