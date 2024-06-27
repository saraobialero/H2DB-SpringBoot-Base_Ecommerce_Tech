package com.ecommerce.service.interfaces;

import com.ecommerce.model.Cart;

import java.util.Optional;

public interface CartFunctions {
    boolean addArticleToCart(String idArticle, int quantity);
    Optional<Cart> getCart(String idCart);
    boolean saveCart(String idCart);

}
