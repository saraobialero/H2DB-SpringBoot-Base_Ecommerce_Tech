package com.ecommerce.service;

import com.ecommerce.exception.ArticleNotFoundException;
import com.ecommerce.exception.CartNotFoundException;
import com.ecommerce.model.Cart;
import com.ecommerce.model.Client;
import com.ecommerce.model.Order;
import com.ecommerce.model.enums.PaymentType;
import com.ecommerce.model.enums.State;
import com.ecommerce.repository.ArticleRepository;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.service.interfaces.OrderFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderFunctions {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Optional<Order> createOrder(int idCart) throws CartNotFoundException {
        Cart cart = cartRepository.findByIdCart(idCart)
                .orElseThrow(() -> new CartNotFoundException(idCart));
        Order order = new Order();
        order.setCart(cart);
        order.setClient(cart.getClient());
        order.setState(State.CONFIRMED);
        order.setPaymentType(PaymentType.NOT_DEFINED);
        int idClient = cart.getClient().getIdClient();
        orderRepository.save(order);
        //Client client;
        //Cart cartN = client.getCart();
        //cart.setOrder(null);
        //System.out.println(cart);

        //cartRepository.deleteCartById(idCart, idClient);

        return Optional.of(order);
    }

    @Override
    public boolean payOrder(int idOrder, PaymentType paymentType) {
        return false;
    }

    @Override
    public boolean deleteOrder(int idOrder) {
        return false;
    }

    @Override
    public List<Order> viewOrders(int idClient) {
        return List.of();
    }
}
