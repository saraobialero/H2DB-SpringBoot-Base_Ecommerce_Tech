package com.ecommerce.service;

import com.ecommerce.exception.ArticleNotFoundException;
import com.ecommerce.exception.CartNotFoundException;
import com.ecommerce.model.*;
import com.ecommerce.model.enums.PaymentType;
import com.ecommerce.model.enums.State;
import com.ecommerce.repository.ArticleRepository;
import com.ecommerce.repository.CartArticleRepository;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.service.interfaces.OrderFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService implements OrderFunctions {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CartArticleRepository cartArticleRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Optional<Order> createOrder(int idCart) throws CartNotFoundException, ArticleNotFoundException {
        //FIND CART
        Cart cart = cartRepository.findByIdCart(idCart)
                .orElseThrow(() -> new CartNotFoundException(idCart));

        // GET CLIENT FROM CART
        Client client = cart.getClient();
        // CREATE NEW ORDER
        Order order = new Order();
        order.setClient(client);
        order.setState(State.ACTIVE);
        order = orderRepository.save(order);

        //CREATE NEW ORDER DETAIL
        createOrderDetails(order, cart);

        //DELETE FIRST CARET ARTICLE AND THEN  CART
        cartArticleRepository.deleteByIdCart(idCart);
        cartRepository.deleteCartById(idCart, client.getIdClient());

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

    private void createOrderDetails(Order order, Cart cart) throws CartNotFoundException, ArticleNotFoundException {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setTotalPrice(cart.getTotalPrice());
        orderDetail.setPaymentType(PaymentType.NOT_DEFINED);
        orderDetail.setOrderDate(LocalDateTime.now());

        List<CartArticle> cartArticles = cartArticleRepository.findByIdCart(cart.getIdCart());

        for (CartArticle cartArticle : cartArticles) {
            int idArticle = cartArticle.getId().getIdArticle();

            //LOAD ARTICLE OBJECT BY ID
            Article article = articleRepository.findById(idArticle)
                    .orElseThrow(() -> new ArticleNotFoundException("Article not found with id: " + idArticle));

            // CREATE ORDER.D.A. FOR EACH ARTICLE
            OrderDetailArticle orderDetailArticle = new OrderDetailArticle();
            orderDetailArticle.setOrderDetail(orderDetail);
            orderDetailArticle.setArticle(article);
            orderDetailArticle.setQuantity(cartArticle.getQuantity());

            // ADD ORDER DETAIL ARTICLE TO COLLECTION ORDER DETAIL
            orderDetail.getOrderDetailArticles().add(orderDetailArticle);
        }

        // SET ORDER DETAIL ON ORDER
        order.setOrderDetail(orderDetail);
        orderRepository.save(order); // SAVE ORDER
    }

}
