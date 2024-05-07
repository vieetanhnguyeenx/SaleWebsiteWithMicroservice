package com.nva.OrderService.service.impl;

import com.nva.OrderService.dto.request.OrderDTORequest;
import com.nva.OrderService.dto.response.InventoryDTOResponse;
import com.nva.OrderService.entity.Order;
import com.nva.OrderService.entity.OrderLineItems;
import com.nva.OrderService.repository.OrderRepository;
import com.nva.OrderService.service.IOrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Array;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
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
    WebClient.Builder webClientBuilder;

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

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();
        // call Inventory service and place order if product is in stock
        InventoryDTOResponse[] result = webClientBuilder.build().get()
                .uri("http://inventory-service/api/v1/inventories",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryDTOResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(result).allMatch(InventoryDTOResponse::isInStock);

        if(allProductsInStock) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock");
        }

    }
}
