package com.victoruk.student_management.dashboard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/students").permitAll()        //.hasRole("ADMIN") // Require 'ADMIN' role for specific endpoints
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(form -> form // Enable form-based login
                        .loginPage("/showMyLoginPage")
                        .defaultSuccessUrl("/home", true) // Redirects to /home after login
                        .loginProcessingUrl("/authenticateTheUser")// Custom login page (optional)
                        .permitAll() // Allow everyone to access the login page
                )
                .logout(logout -> logout // Configure logout functionality
                        .logoutUrl("/logout") // URL to trigger logout
                        .logoutSuccessUrl("/showMyLoginPage") // Redirect to home page with a logout parameter
                        .invalidateHttpSession(true) // Invalidate session on logout
                        .deleteCookies("JSESSIONID") // Delete session cookie
                        .permitAll() // Allow everyone to access logout
                )
                .csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity (not recommended for production);

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails adminUser = User.builder()
                .username("admin") // Shared username
                .password(passwordEncoder().encode("adminpassword"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(adminUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}



















//package com.victoruk.microservices.limits_service.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//public class SecurityCon {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
////                            .anyRequest().hasRole("ADMIN") // Require 'ADMIN' role for all endpoints
//                                .requestMatchers("/api/students/**").hasRole("ADMIN")
//                );
//
//        http.httpBasic(Customizer.withDefaults());
//        http.csrf(csrf -> csrf.disable());
//        return http.build();
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails adminUser = User.builder()
//                .username("admin") // Shared username
//                .password(passwordEncoder().encode("adminpassword"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(adminUser);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}
//
//
