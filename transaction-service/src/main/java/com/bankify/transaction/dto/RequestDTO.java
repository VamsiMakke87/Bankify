package com.bankify.transaction.dto;

import com.bankify.transaction.entity.Requests;
import com.bankify.transaction.util.BankType;
import com.bankify.transaction.util.TransactionType;

public class RequestDTO {
    private String email;

    private TransactionType transactionType;

    private double amount;

    private String  code;

    private BankType bankType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BankType getBankType() {
        return bankType;
    }

    public void setBankType(BankType bankType) {
        this.bankType = bankType;
    }

    public RequestDTO(){}

    public RequestDTO(Requests requests){
        this.amount= requests.getAmount();
        this.email=requests.getEmail();
        this.transactionType=requests.getTransactionType();
        this.code=requests.getCode();
        this.bankType=requests.getBankType();
    }
}
