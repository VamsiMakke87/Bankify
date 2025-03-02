package com.bankify.bank1.repo;

import com.bankify.bank1.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByEmail(String email);


}
