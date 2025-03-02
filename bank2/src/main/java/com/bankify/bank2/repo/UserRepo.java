package com.bankify.bank2.repo;

import com.bankify.bank2.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByEmail(String email);


}
