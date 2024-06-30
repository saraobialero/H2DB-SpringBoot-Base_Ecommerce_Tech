package com.ecommerce.service.interfaces;

import com.ecommerce.exception.*;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CartArticle;
import com.ecommerce.model.enums.PaymentType;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartFunctions {
    @Transactional
    boolean addArticleToCart(String idClient, String idArticle, int quantity) throws ClientNotFoundException, ArticleNotFoundException, InsufficientQuantityException;
    List<Cart> viewClientCarts(String idClient) throws ClientNotFoundException, NoCartsForClientException;
    @Transactional
    boolean saveCart(String idCart) throws CartNotFoundException, CartAlreadyClosedException, CartAlreadySavedException;
    @Transactional
    Optional<Cart> closeCart(String idCart, PaymentType paymentType) throws CartAlreadyClosedException, ArticleNotFoundException;
    List<CartArticle> getCartArticlesByCartId(String idCart);
}
