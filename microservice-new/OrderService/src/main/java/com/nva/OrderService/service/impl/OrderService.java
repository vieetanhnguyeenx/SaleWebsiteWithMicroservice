package com.nva.OrderService.service.impl;

import com.nva.OrderService.dto.request.OrderDTORequest;
import com.nva.OrderService.entity.Order;
import com.nva.OrderService.entity.OrderLineItems;
import com.nva.OrderService.repository.OrderRepository;
import com.nva.OrderService.service.IOrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderService implements IOrderService {

    ModelMapper mapper;
    OrderRepository orderRepository;
    @Override
    public void placeOrder(OrderDTORequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemDTOList()
                .stream()
                .map(orderLineItemDTORequest -> {
                    OrderLineItems items = mapper.map(orderLineItemDTORequest, OrderLineItems.class);
                    items.setOrder(order);
                    return items;
                })
                .collect(Collectors.toList());
        order.setOrderLineItemsList(orderLineItems);

        orderRepository.save(order);
    }
}
