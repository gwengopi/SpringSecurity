package com.home.spring.security.learn.SpringSecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class ProjectSecurityConfig {

//    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        /**
         *  Below is the custom security configurations
         */

      http.authorizeHttpRequests((requests) -> requests.requestMatchers("/getAccDtls","getAccDtls/","/myLoans","/myCards").authenticated()
                        .requestMatchers("/notices","/contact/*").permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
//        return http.build();

        /**
         *  Configuration to deny all the requests
         */
        /*http.authorizeHttpRequests(requests -> requests.anyRequest().denyAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();*/

        /**
         *  Configuration to permit all the requests
         */
      /*  http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();*/


        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/*").authenticated())
    //authorizeHttpRequests((request) -> request.requestMatchers("/getAccDtls").authenticated()
      //          .requestMatchers("/contact/**").permitAll())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
       return http.build();
    }


    @Bean
    public InMemoryUserDetailsManager   defaultApplicationUsers(){
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("Kevin")
                .password("kev")
                .authorities("admin")
                .build();
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("Gwen")
                .password("Gwe")
                .authorities("user")
                .build();

        UserDetails user3 = User.builder()
                .username("Ben")
                .password("ben")
                .authorities("USER")
                .build();
        return  new InMemoryUserDetailsManager(user1,user2,user3);

    }

}