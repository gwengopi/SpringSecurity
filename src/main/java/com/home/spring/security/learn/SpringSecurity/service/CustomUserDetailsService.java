package com.home.spring.security.learn.SpringSecurity.service;

import com.home.spring.security.learn.SpringSecurity.entity.Customer;
import com.home.spring.security.learn.SpringSecurity.repository.CustomerRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(@NonNull String userEmail) throws UsernameNotFoundException {
        String userName;
        List<Customer> customerList = customerRepository.findByEmail(userEmail);
        if(customerList.size() == 0){
            throw new UsernameNotFoundException("Provided user email is not valid");
        }
        Customer retrievedCustomer = customerList.get(0);
        userName = retrievedCustomer.getEmail();
        String password = retrievedCustomer.getPwd();
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(retrievedCustomer.getRole()));
        return  new User(userName,password,grantedAuthorityList);
    }
}
