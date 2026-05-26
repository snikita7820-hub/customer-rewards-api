package com.rewards.customer.util;

import com.rewards.customer.exception.InvalidTransactionException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RewardUtilTest {

    @Test
    void shouldReturn90PointsFor120Amount() {
        int points = RewardUtil.calculateRewardPoints(
                BigDecimal.valueOf(120));

        assertEquals(90, points);
    }

    @Test
    void shouldReturnZeroPointsForAmountBelow50() {
        int points = RewardUtil.calculateRewardPoints(
                BigDecimal.valueOf(40));

        assertEquals(0, points);
    }

    @Test
    void shouldThrowExceptionForNegativeAmount() {
        assertThrows(
                InvalidTransactionException.class,
                () -> RewardUtil.calculateRewardPoints(
                        BigDecimal.valueOf(-10))
        );
    }
}
