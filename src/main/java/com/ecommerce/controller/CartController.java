package com.ecommerce.controller;

import com.ecommerce.model.dto.ArticleDTO;
import com.ecommerce.model.dto.CartArticleDTO;
import com.ecommerce.model.dto.CartDTO;
import com.ecommerce.exception.*;
import com.ecommerce.model.Article;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CartArticle;
import com.ecommerce.service.interfaces.ArticleFunctions;
import com.ecommerce.service.interfaces.CartFunctions;
import com.ecommerce.utilities.JwtUtility;
import io.jsonwebtoken.Claims;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.RoundingMode;
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
    private ArticleFunctions articleFunctions;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtility jwtUtility;


    @GetMapping("cart/client/{idClient}")
    public ResponseEntity<CartDTO> viewCartForClient(@RequestHeader("Authorization") String token,
                                                            @PathVariable("idClient") int idClient ) throws ClientNotFoundException, NoCartsForClientException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));

        Optional<Cart> cartOptional = cartFunctions.viewClientCarts(idClient);

        return cartOptional.map(cart -> {
            CartDTO cartDTO = convertToCartDTO(cart);
            return ResponseEntity.ok(cartDTO);
        }).orElse(ResponseEntity.noContent().build());
    }

    @PostMapping("cart/{idClient}/{idArticle}/{quantity}")
    public ResponseEntity<Integer> addArticleToCarts(@RequestHeader("Authorization") String token,
                                                  @PathVariable("idClient") int idClient,
                                                  @PathVariable("idArticle") int idArticle,
                                                  @PathVariable("quantity") int quantity) throws ClientNotFoundException, ArticleNotFoundException, InsufficientQuantityException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));

        int idCart = cartFunctions.addArticleToCart(idClient, idArticle, quantity);

        return ResponseEntity.ok(idCart);

    }


    @DeleteMapping("/cart/{idCart}/article/{idArticle}")
    public ResponseEntity<Void> deleteArticle(@RequestHeader("Authorization") String token,
                                         @PathVariable("idCart") int idCart,
                                         @PathVariable("idArticle") int idArticle) throws CartNotFoundException, ArticleNotFoundException, CartArticleNotFoundException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));
        cartFunctions.deleteArticle(idCart, idArticle);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/cart/{idCart}/{idClient}")
    public ResponseEntity<Void> deleteCart(@RequestHeader("Authorization") String token,
                                           @PathVariable("idCart") int idCart,
                                           @PathVariable("idClient") int idClient) throws CartNotFoundException, ArticleNotFoundException, CartArticleNotFoundException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));
        cartFunctions.deleteCart(idCart, idClient);
        return ResponseEntity.ok().build();
    }


    //Map cartDTO
    public CartDTO convertToCartDTO(Cart cart) {
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        // Ottenere la lista di CartArticle per il carrello specificato
        List<CartArticle> cartArticles = cartFunctions.getCartArticlesByCartId(cart.getIdCart());

        // Mappatura delle CartArticleDTO
        Set<CartArticleDTO> cartArticleDTOs = cartArticles.stream()
                .map(cartArticle -> {
                    CartArticleDTO cartArticleDTO = new CartArticleDTO();
                    cartArticleDTO.setQuantity(cartArticle.getQuantity());

                    Optional<Article> articleOptional = articleFunctions.getArticleById(cartArticle.getId().getIdArticle());
                    articleOptional.ifPresent(article ->
                            cartArticleDTO.setArticle(modelMapper.map(article, ArticleDTO.class))
                    );
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
