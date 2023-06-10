package com.home.spring.security.learn.SpringSecurity.controller;


import com.home.spring.security.learn.SpringSecurity.aspect.AccountControllerAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("account/")
public class AccountsController {

    @Autowired
    AccountControllerAdvice accountControllerAdvice;

    @GetMapping("/getAccDtls")
//    @PreAuthorize("hasRole('admin','USER','user')")
    @PreAuthorize("hasAnyRole('admin', 'USER', 'user')")
    public String getAccountDetails(){

        // Create an encoder with strength 16
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode("myPassword");
        System.out.println(encoder.matches("myPassword", result));
        System.out.println("inside accounts controller "+Thread.currentThread().getName());
        accountControllerAdvice.asyncMethodCall();;
        return "Account controller details";
    }
}
