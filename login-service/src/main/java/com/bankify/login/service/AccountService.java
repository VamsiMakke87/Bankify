package com.bankify.login.service;


import com.bankify.login.dto.AccountDTO;
import com.bankify.login.entity.Accounts;
import com.bankify.login.repo.AccountRepo;
import com.bankify.login.util.BankType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@Service
public class AccountService {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    KafkaTemplate<Object,Object> kafkaTemplate;

    @Autowired
    private RestTemplate restTemplate;


    public Map<String,Object>  linkAccount(String email, BankType bankType){
        try {

            Accounts accounts= accountRepo.findByEmailAndBankType(email,bankType);
            if(accounts!=null)return Map.of("message","Account already added! Set up a password to activate","isSuccess",false);

            String url = bankType==BankType.BANK1?("http://localhost:8082/bank1/check/"+email):("http://localhost:8083/bank2/check/"+email);
            System.out.println(url);

            boolean hasAccount= restTemplate.getForObject(url, Boolean.class);
            System.out.println(hasAccount);
            if(!hasAccount)return Map.of("message","Not Account of"+bankType+" associated with this email","isSuccess",false);

            Accounts account= new Accounts(email,bankType,1);

            accountRepo.save(account);

            return Map.of("message","Account found! Set up a password to activate","isSuccess",true);

        }catch (Exception e){
            return Map.of("message","Could not process request, please try again","isSuccess",false);
        }


    }

    public Map<String,Object> setPassword(Accounts account){

        try {
            account.setPassword(hashPassword(account.getPassword()));
            account.setActivated(1);
            accountRepo.save(account);
            return Map.of("message","Password set successfully","isSuccess",true);

        }catch (Exception e){
            return Map.of("message","Operation failed, Please try again","isSuccess",false);
        }


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

    public List<AccountDTO> getAccounts(String email){

        List<Accounts> accounts = accountRepo.findByEmail(email);

        List<AccountDTO> li=new ArrayList<>();

        for(Accounts account:accounts)
            li.add(new AccountDTO(account));

         return li;

    }
    
    
    public Long deleteAccount(long id){
        accountRepo.deleteById(id);
        return  id;
    }



}
