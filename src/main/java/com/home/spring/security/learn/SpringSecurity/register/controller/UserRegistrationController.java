package com.home.spring.security.learn.SpringSecurity.register.controller;

import com.home.spring.security.learn.SpringSecurity.entity.Customer;
import com.home.spring.security.learn.SpringSecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customer")
public class UserRegistrationController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registernewUser(@RequestBody Customer customer) {
//        customer.setPwd(passwordEncoder.encode(customer.getPwd()));
        Customer savedCustomer = customerRepository.save(customer);
        if (savedCustomer.getId() > 0)
            return ResponseEntity.status(HttpStatus.OK).body("Customer created successfully");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some error occured during saving customer");
    }

    @GetMapping("/getAllCustomer")
    public ResponseEntity<Object> retrieveAllCustomer() {
        List<Customer> customersWithEmptyPasswords = new ArrayList<>();
        for (Customer customer : customerRepository.findAll()) {
            customer.setPwd("");
            customersWithEmptyPasswords.add(customer);
        }
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(20, TimeUnit.SECONDS)).body(customersWithEmptyPasswords);

    }
}
