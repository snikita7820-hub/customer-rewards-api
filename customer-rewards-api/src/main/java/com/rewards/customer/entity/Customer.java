package com.rewards.customer.entity;

public class Customer {

    private Long custId;

    private String custName;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    @Override
    public String toString() {
        return "Customer [custId=" + custId + ", custName=" + custName + "]";
    }

}
