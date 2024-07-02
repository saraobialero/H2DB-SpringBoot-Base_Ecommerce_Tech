package com.ecommerce.service.interfaces;

import com.ecommerce.exception.*;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CartArticle;
import com.ecommerce.model.enums.PaymentType;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartFunctions {

    boolean addArticleToCart(int idClient, int idArticle, int quantity) throws ClientNotFoundException, ArticleNotFoundException, InsufficientQuantityException;
    List<Cart> viewClientCarts(int idClient) throws ClientNotFoundException, NoCartsForClientException;
    boolean saveCart(int idCart) throws CartNotFoundException, CartAlreadyClosedException, CartAlreadySavedException;

    List<CartArticle> getCartArticlesByCartId(int idCart);

    boolean deleteArticle(int idArticle); //idcart?
    boolean deleteCart(int idCart);
}
