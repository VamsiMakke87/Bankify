package com.bankify.transaction.repo;

import com.bankify.transaction.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepo extends JpaRepository<Transactions, Long> {
    List<Transactions> findByUserEmailOrVendorEmail(String userEmail,String vendorEmail);
}
