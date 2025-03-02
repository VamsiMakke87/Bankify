package com.bankify.transaction.dto;

import com.bankify.transaction.service.TransactionStatus;
import com.bankify.transaction.util.BankType;
import com.bankify.transaction.util.TransactionType;

import java.time.OffsetDateTime;

public class TransactionDTO {

    private long id;
    private String userEmail;

    private String vendorEmail;

    private double amount;

    private TransactionType transactionType;

    private TransactionStatus transactionStatus;


    private BankType userBankType;

    private BankType vendorBankType;

    private String code;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public BankType getUserBankType() {
        return userBankType;
    }

    public void setUserBankType(BankType userBankType) {
        this.userBankType = userBankType;
    }

    public BankType getVendorBankType() {
        return vendorBankType;
    }

    public void setVendorBankType(BankType vendorBankType) {
        this.vendorBankType = vendorBankType;
    }



    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", userEmail='" + userEmail + '\'' +
                ", vendorEmail='" + vendorEmail + '\'' +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                ", transactionStatus=" + transactionStatus +
                ", userBankType=" + userBankType +
                ", vendorBankType=" + vendorBankType +
                ", code='" + code + '\'' +
                '}';
    }
}
