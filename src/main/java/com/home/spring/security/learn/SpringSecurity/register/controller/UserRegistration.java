package com.home.spring.security.learn.SpringSecurity.register.controller;

import com.home.spring.security.learn.SpringSecurity.entity.Customer;
import com.home.spring.security.learn.SpringSecurity.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistration {

    CustomerRepository customerRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registernewUser(Customer customer){
        Customer savedCustomer = customerRepository.save(customer);
        if(savedCustomer.getId()>0)
            return  ResponseEntity.status(HttpStatus.OK).body("Customer created successfully");
       return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some error occured during saving customer");
    }
}
