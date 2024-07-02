package com.ecommerce.service.interfaces;

import com.ecommerce.model.Order;
import com.ecommerce.model.enums.PaymentType;

import java.util.List;

public interface OrderFunctions {
    boolean payOrder(int idOrder, int idCart, PaymentType paymentType);
    boolean deleteOrder(int idOrder);
    List<Order> viewOrders(int idClient);
}
