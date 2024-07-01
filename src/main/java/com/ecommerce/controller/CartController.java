package com.ecommerce.controller;

import com.ecommerce.dto.ArticleDTO;
import com.ecommerce.dto.CartArticleDTO;
import com.ecommerce.dto.CartDTO;
import com.ecommerce.exception.*;
import com.ecommerce.model.Article;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CartArticle;
import com.ecommerce.model.PaymentRequest;
import com.ecommerce.model.enums.PaymentType;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.service.interfaces.ArticleFunctions;
import com.ecommerce.service.interfaces.CartFunctions;
import com.ecommerce.utilities.JwtUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ecommerce/api/v1")
public class CartController {

    @Autowired
    private CartFunctions cartFunctions;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ArticleFunctions articleFunctions;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtility jwtUtility;


    @GetMapping("carts/client/{email}")
    public ResponseEntity<List<CartDTO>> viewCartsForClient(@RequestHeader("Authorization") String token,
                                                            @PathVariable("email") String email ) throws ClientNotFoundException, NoCartsForClientException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));

        List<Cart> carts = cartFunctions.viewClientCarts(email);
        List<CartDTO> cartsDTO = carts.stream()
                .map(this::convertToCartDTO) // Utilizza convertToCartDTO invece di convertToDTO
                .collect(Collectors.toList());

        return carts.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(cartsDTO);

    }
    //TODO: TEST (REMOVE)
    @GetMapping("carts/{idCart}")
    public ResponseEntity<CartDTO> viewCart(@RequestHeader("Authorization") String token,
                                            @PathVariable("idCart") String idCart) {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));

        // Recupera il carrello usando il suo ID
        Optional<Cart> optionalCart = cartRepository.findByIdCart(idCart);

        // Verifica se il carrello è presente
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            CartDTO cartDTO = convertToCartDTO(cart);
            return ResponseEntity.ok(cartDTO);
        } else {
            // Restituisci un 404 Not Found se il carrello non è presente
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("cart/{idClient}/{idArticle}/{quantity}")
    public ResponseEntity<Void> addArticleToCarts(@RequestHeader("Authorization") String token,
                                                  @PathVariable("idClient") String idClient,
                                                  @PathVariable("idArticle") String idArticle,
                                                  @PathVariable("quantity") int quantity) throws ClientNotFoundException, ArticleNotFoundException, InsufficientQuantityException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));

        cartFunctions.addArticleToCart(idClient, idArticle, quantity);

        return ResponseEntity.ok().build();

    }

    @PatchMapping("/cart/state/{idCart}")
    public ResponseEntity<Void> saveCart(@RequestHeader("Authorization") String token,
                                        @PathVariable("idCart") String idCart) throws CartNotFoundException, CartAlreadyClosedException, CartAlreadySavedException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));
        cartFunctions.saveCart(idCart);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cart/close/{idCart}")
    public ResponseEntity<CartDTO> closeCart(@RequestHeader("Authorization") String token,
                                             @PathVariable("idCart") String idCart,
                                             @RequestBody PaymentRequest paymentRequest) throws CartAlreadyClosedException, ArticleNotFoundException, CartNotFoundException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));

        Cart cart = cartFunctions.closeCart(idCart, paymentRequest.getPaymentType()).orElseThrow(
                () -> new CartNotFoundException("Cart not found with id: " + idCart)
        );
        CartDTO cartDTO = convertToCartDTO(cart);
        return ResponseEntity.ok(cartDTO);
    }

    //Map cartDTO
    public CartDTO convertToCartDTO(Cart cart)  {
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        // Ottenere la lista di CartArticle per il carrello specificato
        List<CartArticle> cartArticles = cartFunctions.getCartArticlesByCartId(cart.getIdCart());

        // Mappatura delle CartArticleDTO
        Set<CartArticleDTO> cartArticleDTOs = cartArticles.stream()
                .map(cartArticle -> {
                    CartArticleDTO cartArticleDTO = new CartArticleDTO();
                    cartArticleDTO.setQuantity(cartArticle.getQuantity());

                    Article article = articleFunctions.getArticleById(cartArticle.getId().getIdArticle()).get();
                    cartArticleDTO.setArticle(modelMapper.map(article, ArticleDTO.class));
                    return cartArticleDTO;
                })
                .collect(Collectors.toSet());

        cartDTO.setCartArticles(cartArticleDTOs);

        return cartDTO;
    }


    public <Entity, D> D convertToDTO(Entity entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

}
