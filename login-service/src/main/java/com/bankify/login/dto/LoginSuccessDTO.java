package com.bankify.login.dto;

import com.bankify.login.entity.Users;
import com.bankify.login.util.Role;

public class LoginSuccessDTO {

    private long id;

    private String username;

    private String email;

    private Role role;

    public LoginSuccessDTO(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LoginSuccessDTO(Users user){

        this.id=user.getId();
        this.email=user.getEmail();
        this.username=user.getUsername();
        this.role=user.getRole();

    }

}
