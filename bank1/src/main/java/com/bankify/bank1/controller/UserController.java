package com.bankify.bank1.controller;


import com.bankify.bank1.entity.Users;
import com.bankify.bank1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/bank1")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/signUp")
    public Users signup(@RequestBody  Users user){

        System.out.println(user);

        return service.signup(user);

    }

    @GetMapping("/check/{email}")
    public ResponseEntity<Boolean> check(@PathVariable String email){
        return  ResponseEntity.ok(service.check(email));

    }

    @GetMapping("/getBalance/{email}")
    public ResponseEntity<Map<String,Double>> getBalance(@PathVariable String email){

        return ResponseEntity.ok(Map.of("balance",service.getBalance(email)));
    }

}
