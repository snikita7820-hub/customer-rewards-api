package com.rewards.customer.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardServiceTest {
    private final RewardService service = new RewardService();

    @Test
    void calculateRewardPoints() {

        int points = service.calculatePnts(120,"Test User - 90 points");

        assertEquals(90, points);
    }

    @Test
    void zeroPointsForAmtBelow50() {

        int points = service.calculatePnts(40, "Test User - 40 points");

        assertEquals(0, points);
    }
}
