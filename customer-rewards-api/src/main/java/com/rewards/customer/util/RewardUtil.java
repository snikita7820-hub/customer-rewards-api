package com.rewards.customer.util;

import com.rewards.customer.exception.InvalidTransactionException;

import java.math.BigDecimal;

public class RewardUtil {

    /*
     * calculate reward points based on transaction amount
     */
    public static int calculateRewardPoints(BigDecimal amt) {

        if (amt.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidTransactionException(
                    "Transaction amount cannot be negative");
        }

        if (amt.compareTo(BigDecimal.valueOf(50)) <= 0) {
            return 0;
        }

        if (amt.compareTo(BigDecimal.valueOf(100)) <= 0) {
            return amt.subtract(BigDecimal.valueOf(50))
                    .intValue();
        }

        return amt.subtract(BigDecimal.valueOf(100))
                .multiply(BigDecimal.valueOf(2))
                .add(BigDecimal.valueOf(50))
                .intValue();
    }
}
