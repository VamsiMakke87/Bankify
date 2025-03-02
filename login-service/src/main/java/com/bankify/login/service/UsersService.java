package com.bankify.login.service;


import com.bankify.login.dto.LoginDTO;
import com.bankify.login.dto.LoginSuccessDTO;
import com.bankify.login.entity.Users;
import com.bankify.login.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class UsersService {

    @Autowired
    private UserRepo repo;

    public boolean emailExists(String email){
        Users user= repo.findByEmail(email);
        return  user!=null;
    }

    public boolean usernameExists(String username){
        Users user= repo.findByUsername(username);
        return  user!=null;
    }

    public Users signUp(Users user){
        if(emailExists(user.getEmail()))return null;
        user.setPassword(hashPassword(user.getPassword()));
        return  repo.save(user);

    }

    public LoginSuccessDTO login(LoginDTO loginDTO){
        loginDTO.setPassword(hashPassword(loginDTO.getPassword()));
        Users user= repo.findByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword());
        if(user==null)return null;

        return new LoginSuccessDTO(user);
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

}
