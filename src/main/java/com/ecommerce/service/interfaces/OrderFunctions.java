package com.ecommerce.service.interfaces;

import com.ecommerce.exception.CartNotFoundException;
import com.ecommerce.model.Order;
import com.ecommerce.model.enums.PaymentType;

import java.util.List;
import java.util.Optional;

public interface OrderFunctions {
    Optional<Order> createOrder(int idCart) throws CartNotFoundException;
    boolean payOrder(int idOrder, PaymentType paymentType);
    boolean deleteOrder(int idOrder);
    List<Order> viewOrders(int idClient);
}
