package org.example.service;

import org.example.CustomerRegistrationRequest;
import org.example.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public record CustomerService() {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
//        TODO: check if email is valid
//        TODO: check if email not taken
//        TODO: store customer in DB
    }
}
