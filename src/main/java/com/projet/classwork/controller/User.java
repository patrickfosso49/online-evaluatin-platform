package com.projet.classwork.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class User {

    private long id;
    private String registrationNumber;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;

}
