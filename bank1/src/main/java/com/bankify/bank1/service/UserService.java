package com.bankify.bank1.service;


import com.bankify.bank1.entity.Users;
import com.bankify.bank1.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public Users signup(Users user){
        return repo.save(user);
    }

    public boolean check(String email){

        Users user= repo.findByEmail(email);

        return user!=null;

    }

    public double getBalance(String email){

        Users user= repo.findByEmail(email);
        if(user==null)return -1;
        return  user.getBalance();

    }

    @Transactional
    public Users doTransaction(Map<String,Object> transaction){

        Users user= repo.findByEmail((String)transaction.get("email"));

        double amount= (double) transaction.get("amount");

        user.setBalance(user.getBalance()+amount);

        return  user;


    }



}
