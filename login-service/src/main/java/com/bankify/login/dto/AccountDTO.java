package com.bankify.login.dto;

import com.bankify.login.entity.Accounts;
import com.bankify.login.util.BankType;

public class AccountDTO {

    private Long id;

    private String email;

    private BankType bankType;

    private int isActivated;

    public AccountDTO(Accounts account){
        this.id=account.getId();
        this.email= account.getEmail();
        this.bankType= account.getBankType();
        this.isActivated=account.isActivated();
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", bankType=" + bankType +
                ", isActivated=" + isActivated +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(int isActivated) {
        this.isActivated = isActivated;
    }
}
