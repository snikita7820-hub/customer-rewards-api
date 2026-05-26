package com.rewards.customer.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity class representing customer transaction details.
 * <p>
 * This entity stores purchase transaction information
 * used for reward point calculation.
 * <p>
 * Mapped Database Table:
 * customer_transaction
 * <p>
 * Each transaction contains:
 * <p>
 * Transaction id
 * Customer id
 * Transaction amount
 * Transaction date
 */
@Entity
@Table(name = "customer_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Transaction ID", example = "1")
    private long txnId;

    @NotBlank(message = "Customer ID cannot be blank")
    @Schema(description = "Customer ID", example = "CUST001")
    private String custId;

    @Positive(message = "Amount must be greater than 0")
    @Schema(description = "Transaction amount", example = "120")
    private BigDecimal amount;

    @NotNull(message = "Transaction date cannot be null")
    @PastOrPresent(message = "transaction date cannot be in the future")
    @Schema(description = "Transaction date", example = "2026-05-20")
    private LocalDate date;

    public long getTxnId() {
        return txnId;
    }

    public void setTxnId(long txnId) {
        this.txnId = txnId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction [txnId=" + txnId + ", custId=" + custId + ", amount=" + amount + ", date=" + date + "]";
    }

}