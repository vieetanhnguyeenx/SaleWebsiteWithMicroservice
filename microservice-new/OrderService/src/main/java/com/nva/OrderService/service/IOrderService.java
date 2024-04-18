package com.nva.OrderService.service;

import com.nva.OrderService.dto.request.OrderDTORequest;

public interface IOrderService {
    void placeOrder(OrderDTORequest orderRequest);
}
