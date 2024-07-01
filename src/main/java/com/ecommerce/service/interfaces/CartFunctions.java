package com.ecommerce.service.interfaces;

import com.ecommerce.exception.*;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CartArticle;
import com.ecommerce.model.enums.PaymentType;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartFunctions {

    boolean addArticleToCart(String idClient, String idArticle, int quantity) throws ClientNotFoundException, ArticleNotFoundException, InsufficientQuantityException;
    List<Cart> viewClientCarts(String email) throws ClientNotFoundException, NoCartsForClientException;
    boolean saveCart(String idCart) throws CartNotFoundException, CartAlreadyClosedException, CartAlreadySavedException;
    Optional<Cart> closeCart(String idCart, PaymentType paymentType) throws CartAlreadyClosedException, ArticleNotFoundException;
    List<CartArticle> getCartArticlesByCartId(String idCart);
    boolean deleteArticle(String idArticle); //idcart?
    boolean deleteCart(String idCart);
}
