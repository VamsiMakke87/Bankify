package com.bankify.login.controller;


import com.bankify.login.dto.LoginDTO;
import com.bankify.login.dto.LoginSuccessDTO;
import com.bankify.login.entity.Users;
import com.bankify.login.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/signUp")
    public ResponseEntity<String> singUp(@RequestBody Users user){

        try{
            user= usersService.signUp(user);
            if(user!=null){
                return  ResponseEntity.ok("Account created successfully");
            }

            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists!");

        }catch (Exception e){

            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Occured, Please try again later");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginSuccessDTO> login(@RequestBody LoginDTO loginDTO){

        try{
            LoginSuccessDTO user= usersService.login(loginDTO);
            if(user!=null){
                return  ResponseEntity.ok(user);
            }

            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }catch (Exception e){

            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
