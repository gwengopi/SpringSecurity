package com.home.spring.security.learn.SpringSecurity.repository;

import com.home.spring.security.learn.SpringSecurity.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    public List<Customer> findByEmail(String email);
}
