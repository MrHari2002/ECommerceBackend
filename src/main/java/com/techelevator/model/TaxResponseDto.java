package com.techelevator.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TaxResponseDto {
    private String state;
    private BigDecimal salesTax;
    private String lastUpdated;

    public BigDecimal getSalesTax() {
        return salesTax;
    }

    public String getState() {
        return state;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;
    }

    public void setState(String state) {
        this.state = state;
    }
}
