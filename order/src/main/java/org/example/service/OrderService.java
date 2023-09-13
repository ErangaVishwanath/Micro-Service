package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.exception.CustomerServiceException;
import org.example.repository.OrderRepository;
import org.example.response.OrderResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private ModelMapper mapper;
    private final RestTemplate restTemplate;

    public OrderResponse getOrderById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        return mapper.map(order, OrderResponse.class);
    }

    public Order saveOrder(Order order) throws CustomerServiceException {
        int customerId = order.getCustomerId();
        order.setCustomerId(customerId);
        return orderRepository.save(order);
    }

    public Customer fetchCustomer(Integer customerId) throws CustomerServiceException {
        String customerServiceUrl = "";
        try {
            ResponseEntity<Customer> response = restTemplate.getForEntity(customerServiceUrl, Customer.class, customerId);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                // Handle the case when the customer is not found
                // You can log the error or take appropriate action
                return null;
            } else {
                // Handle other HTTP status codes as needed
                throw new CustomerServiceException("Error fetching customer: " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            throw new CustomerServiceException("Error communicating with customer service: " + e.getMessage(), e);
        }

    }
}
