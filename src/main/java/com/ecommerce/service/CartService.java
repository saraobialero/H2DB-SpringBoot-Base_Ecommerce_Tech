package com.ecommerce.service;

import com.ecommerce.exception.*;
import com.ecommerce.model.*;
import com.ecommerce.model.enums.State;
import com.ecommerce.repository.ArticleRepository;
import com.ecommerce.repository.CartArticleRepository;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.service.interfaces.CartFunctions;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements CartFunctions {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CartArticleRepository cartArticleRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public boolean addArticleToCart(int idClient, int idArticle, int quantity) throws ClientNotFoundException, ArticleNotFoundException, InsufficientQuantityException {

        // GET CLIENT FROM CLIENT REPOSITORY
        Client client = clientService.getClientById(idClient)
                .orElseThrow(() -> new ClientNotFoundException("Client with " + idClient + " not found"));

        // FIND THE ARTICLE FROM ARTICLE REPOSITORY
        Article article = articleService.existArticle(idArticle)
                .orElseThrow(() -> new ArticleNotFoundException("Article with " + idArticle + " not found"));

        // CHECK QUANTITY AVAILABLE
        if (quantity > article.getAvailableQuantity()) {
            throw new InsufficientQuantityException();
        }

        // CHECK IF CLIENT ALREADY HAS CART
        Cart cart = cartRepository.findByIdClient(idClient)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setClient(client);
                    newCart.setState(State.IN_PROGRESS);
                    newCart.setTotalPrice(0); //INITIALIZE
                    return cartRepository.save(newCart);
                });

        // CREATE ID FOR CART ARTICLE (DOUBLE KEY)
        CartArticleId cartArticleId = new CartArticleId(cart.getIdCart(), idArticle);

        // FIND OR CREATE A NEW CartArticle
        CartArticle cartArticle = cartArticleRepository.findById(cartArticleId)
                .orElseGet(() -> {
                    CartArticle newCartArticle = new CartArticle();
                    newCartArticle.setId(cartArticleId);
                    newCartArticle.setQuantity(0); //INITIALIZE
                    return newCartArticle;
                });

        // UPDATE QUANTITY IF THE ARTICLE IS ALREADY IN THE CART
        int newQuantity = cartArticle.getQuantity() + quantity;
        cartArticle.setQuantity(newQuantity);
        cartArticleRepository.save(cartArticle);

        // UPDATE TOTAL PRICE OF THE CART
        cart.setTotalPrice(cart.getTotalPrice() + (article.getPrice() * quantity));

        return true;
    }

    @Override
    public List<Cart> viewClientCarts(int idClient) throws ClientNotFoundException, NoCartsForClientException {
        Optional<Client> client = clientService.getClientById(idClient);
        if (client.isEmpty()) throw new ClientNotFoundException("Client not found");


        List<Cart> carts = cartRepository.findAllByClientId(idClient);
        if (carts.isEmpty()) throw new NoCartsForClientException("No carts for client:" + idClient);

        return carts;
    }

    @Transactional
    @Override
    public boolean saveCart(int idCart) throws CartNotFoundException, CartAlreadyClosedException, CartAlreadySavedException {
        Cart cart = cartRepository.findByIdCart(idCart)
                .orElseThrow(() -> new CartNotFoundException(idCart));

        //SAVE CART
        cart.setState(State.SAVED);
        cartRepository.save(cart);
        return true;
    }


    @Override
    public List<CartArticle> getCartArticlesByCartId(int idCart) {
        return cartArticleRepository.findByIdCart(idCart);
    }


    @Override
    public boolean deleteArticle(int idCart, int idArticle) throws CartNotFoundException, ArticleNotFoundException, CartArticleNotFoundException {
        Cart cart = cartRepository.findByIdCart(idCart)
                .orElseThrow(() -> new CartNotFoundException(idCart));

        CartArticleId cartArticleId = new CartArticleId(idCart, idArticle);
        CartArticle cartArticle = cartArticleRepository.findById(cartArticleId)
                .orElseThrow(() ->  new CartArticleNotFoundException("cart article not found with id: " + cartArticleId));


        cartArticleRepository.deleteById(cartArticleId);
        return true;
    }

    @Transactional
    @Override
    public boolean deleteCart(int idCart, int idClient) throws CartNotFoundException {
        Cart cart = cartRepository.findByIdCart(idCart)
                .orElseThrow(() -> new CartNotFoundException(idCart));
        cartArticleRepository.deleteByIdCart(idCart);

        cartRepository.deleteCartById(idCart, idClient);
        return true;
    }




}
