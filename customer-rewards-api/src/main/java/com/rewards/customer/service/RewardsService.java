package com.rewards.customer.service;

import com.rewards.customer.dto.Reward;
import com.rewards.customer.entity.Transaction;

import java.util.List;

public interface RewardsService {

    List<Reward> getrewardpoints(List<Transaction> trans);

    List<Transaction> getTransactionList();

}
