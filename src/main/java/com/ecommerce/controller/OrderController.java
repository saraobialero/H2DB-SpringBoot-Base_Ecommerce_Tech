package com.ecommerce.controller;

import com.ecommerce.dto.*;
import com.ecommerce.exception.*;
import com.ecommerce.model.*;
import com.ecommerce.service.ArticleService;
import com.ecommerce.service.CartService;
import com.ecommerce.service.interfaces.OrderFunctions;
import com.ecommerce.utilities.JwtUtility;
import io.jsonwebtoken.Claims;
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
public class OrderController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private OrderFunctions orderFunctions;

    @Autowired
    private CartService cartService;

    @Autowired
    private ArticleService articleService;

    @PostMapping("order/{idCart}")
    public ResponseEntity<OrderDTO> createOrder(@RequestHeader("Authorization") String token,
                                                @PathVariable("idCart") int idCart) throws CartNotFoundException, OrderNotFoundException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));
        Order order = orderFunctions.createOrder(idCart)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        OrderDTO orderDTO = convertToOrderDto(order);

        return ResponseEntity.ok(orderDTO);

    }

    public OrderDTO convertToOrderDto(Order order) {
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);

        // Mappa il carrello (Cart) associato all'ordine (Order)
        Cart cart = order.getCart();
        if (cart != null) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setIdCart(cart.getIdCart());
            cartDTO.setTotalPrice(cart.getTotalPrice());
            cartDTO.setState(cart.getState());

            List<CartArticle> cartArticles = cartService.getCartArticlesByCartId(cart.getIdCart());

            // Mappatura delle CartArticleDTO
            Set<CartArticleDTO> cartArticleDTOs = cartArticles.stream()
                    .map(cartArticle -> {
                        CartArticleDTO cartArticleDTO = new CartArticleDTO();
                        cartArticleDTO.setQuantity(cartArticle.getQuantity());

                        Article article = articleService.getArticleById(cartArticle.getId().getIdArticle()).orElse(null);
                        if (article != null) {
                            cartArticleDTO.setArticle(modelMapper.map(article, ArticleDTO.class));
                        } else {
                            // Gestione caso in cui l'articolo non è presente
                            // Esempio: cartArticleDTO.setArticle(new ArticleDTO());
                        }
                        return cartArticleDTO;
                    })
                    .collect(Collectors.toSet());

            cartDTO.setCartArticles(cartArticleDTOs);
            orderDTO.setCart(cartDTO); // Imposta il cartDTO nell'ordineDTO
        }

        // Mappa il cliente (Client) associato all'ordine (Order)
     /*   Client client = order.getClient();
        if (client != null) {
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setIdClient(client.getIdClient());
            clientDTO.setEmail(client.getEmail());
            clientDTO.setName(client.getName());
            clientDTO.setSurname(client.getSurname());

            orderDTO.setClient(clientDTO); // Imposta il clientDTO nell'ordineDTO
        } */

        return orderDTO;
    }




        public <Entity, D> D convertToDTO(Entity entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}
