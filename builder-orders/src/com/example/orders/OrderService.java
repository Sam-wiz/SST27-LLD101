package com.example.orders;

import java.util.List;

public class OrderService {

    public Order createOrder(String id, String email, List<OrderLine> lines, Integer discount, boolean expedited, String notes) {
        Order.Builder builder = new Order.Builder(id, email);
        
        if (lines != null) {
            for (OrderLine line : lines) {
                builder.addLine(line);
            }
        }
        
        return builder
                .discountPercent(discount)
                .expedited(expedited)
                .notes(notes)
                .build();
    }
}
