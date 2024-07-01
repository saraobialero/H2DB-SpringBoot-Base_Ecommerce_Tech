package com.ecommerce.service;

import com.ecommerce.dto.ArticleDTO;
import com.ecommerce.dto.CartArticleDTO;
import com.ecommerce.dto.CartDTO;
import com.ecommerce.exception.*;
import com.ecommerce.model.*;
import com.ecommerce.model.enums.PaymentType;
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
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
        /*
        // CHECK IF CLIENT ALREADY HAS 'IN_PROGRESS' CART (NOT: 'CLOSED')
        Cart cart = cartRepository.findActiveCartByClientIdAndStateNot(idClient, State.CLOSED)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    //newCart.setIdCart(); AUTOINCREMENT
                    newCart.setClient(client);
                    //newCart.setPaymentType(PaymentType.NOT_DEFINED);
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
                    return cartArticleRepository.save(newCartArticle);
                });

        // UPDATE QUANTITY IF THE ARTICLE IS ALREADY IN THE CART
        int newQuantity = cartArticle.getQuantity() + quantity;
        cartArticle.setQuantity(newQuantity);
        cartArticleRepository.save(cartArticle);

        // UPDATE TOTAL PRICE OF THE CART
        cart.setTotalPrice(cart.getTotalPrice() + (article.getPrice() * quantity));
        cartRepository.save(cart); */

        return true;
    }


    @Override
    public List<Cart> viewClientCarts(String email) throws ClientNotFoundException, NoCartsForClientException {
        Optional<Client> client = clientService.getClientByEmail(email);
        if (client.isEmpty()) throw new ClientNotFoundException("Client not found");

        int idClient = client.get().getIdClient();

        List<Cart> carts = cartRepository.findAllByClientId(idClient);
        if (carts.isEmpty()) throw new NoCartsForClientException(email);

        return carts;
    }

    @Transactional
    @Override
    public boolean saveCart(int idCart) throws CartNotFoundException, CartAlreadyClosedException, CartAlreadySavedException {
        Cart cart = cartRepository.findByIdCart(idCart)
                .orElseThrow(() -> new CartNotFoundException(idCart));
/*
        //CHECK STATE
        if (cart.getState().equals(State.CLOSED)) {
            throw new CartAlreadyClosedException("Cart already closed");
        } else if (cart.getState().equals(State.SAVED)) {
            throw new CartAlreadySavedException("Cart already saved");
        }

        //UPDATE CART STATUS
        cart.setState(State.SAVED);*/
        cartRepository.save(cart);
        return true;
    }

    @Transactional
    @Override
    public Optional<Cart> closeCart(int idCart, PaymentType paymentType) throws CartAlreadyClosedException, ArticleNotFoundException {
    /*    Cart cart = cartRepository.findActiveCartByCartIdAndStateNot(idCart, State.CLOSED)
                .orElseThrow(() -> new CartAlreadyClosedException("Cart already closed"));
    //    cart.setState(State.CLOSED);
   //     cart.setPaymentType(paymentType);

        //FIND CART ARTICLE ASSOCIATED
        List<CartArticle> cartArticles = cartArticleRepository.findByIdCart(idCart);
        for (CartArticle cartArticle : cartArticles) {
            int idArticle = cartArticle.getId().getIdArticle();
            Article article = articleRepository.findById(idArticle).orElseThrow(() -> new ArticleNotFoundException("Article not found"));
            article.setAvailableQuantity(article.getAvailableQuantity() - cartArticle.getQuantity());
            articleRepository.save(article);
        }

        cartRepository.save(cart);
        return Optional.of(cart);*/
        return Optional.empty();
    }

    @Override
    public List<CartArticle> getCartArticlesByCartId(int idCart) {
        return List.of();
    }

    @Override
    public boolean deleteArticle(int idArticle) {
        return false;
    }

    @Override
    public boolean deleteCart(int idCart) {
        return false;
    }


}
