package com.bankify.login.controller;


import com.bankify.login.dto.AccountDTO;
import com.bankify.login.dto.LinkAccountDTO;
import com.bankify.login.entity.Accounts;
import com.bankify.login.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountsController {

    @Autowired
    private AccountService accountService;



    @PostMapping("/link")
    public ResponseEntity<Map<String, Object>> checkAccount(@RequestBody LinkAccountDTO dto){

        try{

            Map<String, Object> checkAccount = accountService.linkAccount(dto.getEmail(), dto.getBankType());
            if((boolean)checkAccount.get("isSuccess"))
                return  ResponseEntity.ok(checkAccount);
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(checkAccount);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","Server error! Please try again","isSuccess",false));
        }



    }

    @PutMapping("/setPassword")
    public ResponseEntity<Map<String,Object>> setPassword(@RequestBody Accounts accounts){

        try{
            return  ResponseEntity.ok(accountService.setPassword(accounts));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","Server error! Please try again","isSuccess",false));
        }

    }

    @GetMapping("/getAccounts")
    public ResponseEntity<List<AccountDTO>> getAccounts(@RequestParam String email){

        return ResponseEntity.ok(accountService.getAccounts(email));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String,Object>> deleteAccount(@RequestParam long id){
        try{
             ResponseEntity.ok(accountService.deleteAccount(id));
             return ResponseEntity.ok(Map.of("message","Delete account successful","isSuccess",true));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","Delete account failed","isSuccess",false));
        }

    }


}
