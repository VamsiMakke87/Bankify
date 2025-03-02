package com.bankify.login.repo;

import com.bankify.login.entity.Users;
import com.bankify.login.util.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo  extends JpaRepository<Users,Long> {


    public Users findByEmailAndPassword(String email, String password);
    public Users findByEmail(String email);

    public Users findByUsername(String username);
}
