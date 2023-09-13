package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.exception.CustomerServiceException;
import org.example.response.OrderResponse;
import org.example.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @GetMapping("/orders/{id}")
    private ResponseEntity<OrderResponse> getOrderDetails(@PathVariable("id") int id) throws CustomerServiceException {
        OrderResponse order = orderService.getOrderById(id);
        Customer customer = orderService.fetchCustomer(order.getCustomerId());
        order.setCustomer(customer);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PostMapping("/create-order")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) throws CustomerServiceException {
        Order createdOrder = orderService.saveOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
}
