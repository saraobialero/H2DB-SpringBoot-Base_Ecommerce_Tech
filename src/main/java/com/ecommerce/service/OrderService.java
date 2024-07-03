package com.ecommerce.service;

import com.ecommerce.exception.*;
import com.ecommerce.model.*;
import com.ecommerce.model.enums.PaymentType;
import com.ecommerce.model.enums.State;
import com.ecommerce.repository.*;
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
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CartArticleRepository cartArticleRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Optional<Order> createOrder(int idCart) throws CartNotFoundException, ArticleNotFoundException, InsufficientQuantityException {
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

        //REMOVE QUANTITY OF ARTICLES
        removeArticlesQuantity(cart);

        //DELETE FIRST CARET ARTICLE AND THEN  CART
        cartArticleRepository.deleteByIdCart(idCart);
        cartRepository.deleteCartById(idCart, client.getIdClient());


        return Optional.of(order);
    }

    @Override
    public boolean payOrder(int idOrder, PaymentType paymentType) throws OrderNotFoundException, NoOrderDetailForOrderException, OrderAnnulledException {
        //FIND ORDER
        Order order = orderRepository.findById(idOrder)
                .orElseThrow(() -> new OrderNotFoundException("order not found"));
        int idOrderDetail = order.getOrderDetail().getId();
        //FIND ORDER DETAIL TO SET TYPE OF PAYMENT AND HOUR
        OrderDetail orderDetail = orderDetailRepository.findById(idOrderDetail)
                .orElseThrow(() -> new NoOrderDetailForOrderException("Order detail Not found for order"));
        if (order.getState().equals(State.ANNULLED)) throw new OrderAnnulledException("Order annulled");

        order.setState(State.CLOSED);
        orderRepository.save(order);

        orderDetail.setOrderDate(LocalDateTime.now());
        orderDetail.setPaymentType(paymentType);
        orderDetailRepository.save(orderDetail);
        return true;
    }

    @Override
    public boolean deleteOrder(int idOrder) throws OrderNotFoundException, OrderAlreadyClosedException, ArticleNotFoundException {

        //FIND ORDER
        Order order = orderRepository.findById(idOrder)
                .orElseThrow(() -> new OrderNotFoundException("order not found"));
        if(order.getState().equals(State.CLOSED)) throw new OrderAlreadyClosedException("Order already closed");

        // RESTORE THE QUANTITY OF ARTICLES
        restoreArticlesQuantity(order);

        order.setState(State.ANNULLED);
        orderRepository.save(order);
        return true;
    }

    @Override
    public List<Order> viewOrders(int idClient) {
        return orderRepository.findByIdClient(idClient);
    }


    private void removeArticlesQuantity(Cart cart) throws ArticleNotFoundException, InsufficientQuantityException {
        List<CartArticle> cartArticles = cartArticleRepository.findByIdCart(cart.getIdCart());

        for (CartArticle cartArticle : cartArticles) {
            int idArticle = cartArticle.getId().getIdArticle();
            int quantity = cartArticle.getQuantity();

            Article article = articleRepository.findById(idArticle)
                    .orElseThrow(() -> new ArticleNotFoundException("Article not found with id: " + idArticle));

            // Rimuovi la quantità dal magazzino
            int newQuantity = article.getAvailableQuantity() - quantity;
            if (newQuantity < 0) {
                throw new InsufficientQuantityException();
            }

            article.setAvailableQuantity(newQuantity);
            articleRepository.save(article);
        }
    }

    private void restoreArticlesQuantity(Order order) throws ArticleNotFoundException {
        OrderDetail orderDetail = order.getOrderDetail();
        Set<OrderDetailArticle> orderDetailArticles = orderDetail.getOrderDetailArticles();

        for (OrderDetailArticle orderDetailArticle : orderDetailArticles) {
            Article article = orderDetailArticle.getArticle();
            int quantity = orderDetailArticle.getQuantity();
            article.setAvailableQuantity(article.getAvailableQuantity() + quantity);
            articleRepository.save(article);
        }
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
