package com.bankify.login.dto;

import com.bankify.login.util.BankType;

public class LinkAccountDTO {

    private String email;

    private BankType bankType;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BankType getBankType() {
        return bankType;
    }

    public void setBankType(BankType bankType) {
        this.bankType = bankType;
    }
}
