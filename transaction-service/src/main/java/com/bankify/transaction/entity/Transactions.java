package com.bankify.transaction.entity;


import com.bankify.transaction.dto.TransactionDTO;
import com.bankify.transaction.service.TransactionStatus;
import com.bankify.transaction.util.BankType;
import com.bankify.transaction.util.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userEmail;

    private String vendorEmail;

    private double amount;

    private TransactionType transactionType;

    private TransactionStatus transactionStatus;


    private BankType userBankType;

    private BankType vendorBankType;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    public  Transactions(){}
    public Transactions(TransactionDTO transactionDTO) {

        this.amount=transactionDTO.getAmount();
        this.userBankType=transactionDTO.getUserBankType();
        this.vendorBankType=transactionDTO.getVendorBankType();
        this.userEmail=transactionDTO.getUserEmail();
        this.vendorEmail=transactionDTO.getVendorEmail();
        this.transactionType=transactionDTO.getTransactionType();
        this.transactionStatus= TransactionStatus.INPROGRESS;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
