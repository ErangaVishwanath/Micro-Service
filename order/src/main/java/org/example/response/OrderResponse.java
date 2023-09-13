package org.example.response;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.Customer;
import org.example.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class OrderResponse {
    private int id;
    private int customerId;
    private LocalDateTime orderDate;
    private String shippingAddress;
    private List<OrderItem> items;
    private Customer customer;
}
