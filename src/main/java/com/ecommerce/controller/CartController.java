package com.ecommerce.controller;

import com.ecommerce.dto.ArticleDTO;
import com.ecommerce.dto.CartArticleDTO;
import com.ecommerce.dto.CartDTO;
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

import java.util.List;
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
    public ResponseEntity<List<CartDTO>> viewCartForClient(@RequestHeader("Authorization") String token,
                                                            @PathVariable("idClient") int idClient ) throws ClientNotFoundException, NoCartsForClientException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));

        List<Cart> carts = cartFunctions.viewClientCarts(idClient);
        List<CartDTO> cartsDTO = carts.stream()
                .map(this::convertToCartDTO)
                .collect(Collectors.toList());

        return carts.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(cartsDTO);

    }

    @PostMapping("cart/{idClient}/{idArticle}/{quantity}")
    public ResponseEntity<Void> addArticleToCarts(@RequestHeader("Authorization") String token,
                                                  @PathVariable("idClient") int idClient,
                                                  @PathVariable("idArticle") int idArticle,
                                                  @PathVariable("quantity") int quantity) throws ClientNotFoundException, ArticleNotFoundException, InsufficientQuantityException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));

        cartFunctions.addArticleToCart(idClient, idArticle, quantity);

        return ResponseEntity.ok().build();

    }

    @PatchMapping("/cart/state/{idCart}")
    public ResponseEntity<Void> saveCart(@RequestHeader("Authorization") String token,
                                        @PathVariable("idCart") int idCart) throws CartNotFoundException, CartAlreadyClosedException, CartAlreadySavedException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));
        cartFunctions.saveCart(idCart);
        return ResponseEntity.ok().build();
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
