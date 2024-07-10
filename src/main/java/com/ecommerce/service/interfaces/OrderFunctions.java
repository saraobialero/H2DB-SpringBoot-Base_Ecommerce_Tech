package com.ecommerce.service.interfaces;

import com.ecommerce.exception.*;
import com.ecommerce.model.Order;
import com.ecommerce.model.enums.PaymentType;

import java.util.List;
import java.util.Optional;

public interface OrderFunctions {
    int createOrder(int idCart) throws CartNotFoundException, ArticleNotFoundException, InsufficientQuantityException;
    boolean payOrder(int idOrder, PaymentType paymentType) throws OrderNotFoundException, NoOrderDetailForOrderException, OrderAnnulledException;
    boolean deleteOrder(int idOrder) throws OrderNotFoundException, OrderAlreadyClosedException, ArticleNotFoundException;
    List<Order> viewOrders(int idClient);
    Optional<Order> viewOrderById(int idOrder) throws OrderNotFoundException;
}
