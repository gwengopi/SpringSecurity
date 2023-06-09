package com.home.spring.security.learn.SpringSecurity.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class AccountControllerAdvice {


    @Async
    public void asyncMethodCall(){
        System.out.println("Async method call "+Thread.currentThread().getName());
    }
}
