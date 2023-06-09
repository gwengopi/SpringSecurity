package com.home.spring.security.learn.SpringSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactsController {

    @GetMapping("/getContact")
    public String getContactDetails(){
        return "Bank contact details";
    }
}
