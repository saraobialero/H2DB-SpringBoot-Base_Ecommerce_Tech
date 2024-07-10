package com.ecommerce.service.interfaces;

import com.ecommerce.exception.*;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CartArticle;
import com.ecommerce.model.enums.PaymentType;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartFunctions {

    int addArticleToCart(int idClient, int idArticle, int quantity) throws ClientNotFoundException, ArticleNotFoundException, InsufficientQuantityException;
    List<Cart> viewClientCarts(int idClient) throws ClientNotFoundException, NoCartsForClientException;
    List<CartArticle> getCartArticlesByCartId(int idCart);
    boolean deleteArticle(int idCart, int idArticle) throws CartNotFoundException, ArticleNotFoundException, CartArticleNotFoundException;
    boolean deleteCart(int idCart, int idClient) throws CartNotFoundException;
}
