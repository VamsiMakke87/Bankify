package com.bankify.login.repo;

import com.bankify.login.entity.Accounts;
import com.bankify.login.util.BankType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepo extends JpaRepository<Accounts,Long> {

    public Accounts findByEmailAndBankType(String email, BankType bankType);

    public List<Accounts> findByEmail(String email);

}
