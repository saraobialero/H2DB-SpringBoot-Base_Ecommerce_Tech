package com.ecommerce.controller;

import com.ecommerce.exception.*;
import com.ecommerce.model.*;
import com.ecommerce.model.dto.*;
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

    @PostMapping("order/cart/{idCart}")
    public ResponseEntity<Integer> createOrder(@RequestHeader("Authorization") String token,
                                                @PathVariable("idCart") int idCart) throws CartNotFoundException, OrderNotFoundException, ArticleNotFoundException, InsufficientQuantityException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));
        int idOrder = orderFunctions.createOrder(idCart);

        return ResponseEntity.ok(idOrder);
    }

    @PostMapping("order/{idOrder}")
    public ResponseEntity<Void> payOrder(@RequestHeader("Authorization") String token,
                                         @PathVariable("idOrder") int idOrder,
                                         @RequestBody PaymentRequest paymentRequest) throws OrderNotFoundException, NoOrderDetailForOrderException, OrderAnnulledException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));

        boolean paymentSuccessfully = orderFunctions.payOrder(idOrder, paymentRequest.getPaymentType());
        return paymentSuccessfully ?
                ResponseEntity.ok().build() :
                ResponseEntity.badRequest().build();
    }

    @PatchMapping("state/{idOrder}")
    public ResponseEntity<Void> deleteOrder(@RequestHeader("Authorization") String token,
                                            @PathVariable("idOrder") int idOrder) throws OrderNotFoundException, NoOrderDetailForOrderException, OrderAlreadyClosedException, ArticleNotFoundException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));

        boolean deletedSuccessfully = orderFunctions.deleteOrder(idOrder);
        return deletedSuccessfully ?
                ResponseEntity.ok().build() :
                ResponseEntity.badRequest().build();
    }

    @GetMapping("client/orders/{idClient}")
    public ResponseEntity<List<OrderDTO>> viewOrdersForClient(@RequestHeader("Authorization") String token,
                                                              @PathVariable("idClient") int idClient) throws OrderNotFoundException, NoOrderDetailForOrderException, OrderAlreadyClosedException, ArticleNotFoundException, NoOrderForClientException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));

        List<Order> orders = orderFunctions.viewOrders(idClient);
        List<OrderDTO> ordersDTO = orders.stream()
                .map(order -> convertToDTO(order, OrderDTO.class))
                .collect(Collectors.toList());

        if (ordersDTO.isEmpty()) throw new NoOrderForClientException("no order for client with id: " + idClient);

        return ResponseEntity.ok(ordersDTO);
    }

    @GetMapping("order/{idOrder}")
    public ResponseEntity<OrderDTO> viewOrderByIdOrder(@RequestHeader("Authorization") String token,
                                                       @PathVariable("idOrder") int idOrder) throws OrderNotFoundException, NoOrderDetailForOrderException, OrderAlreadyClosedException, ArticleNotFoundException, NoOrderForClientException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));

        Order order = orderFunctions.viewOrderById(idOrder)
                        .orElseThrow(() -> new OrderNotFoundException("order not found"));
        OrderDTO orderDTO = convertToOrderDto(order);

        return ResponseEntity.ok(orderDTO);
    }

    private OrderDTO convertToOrderDto(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setIdOrder(order.getIdOrder());
        orderDTO.setState(order.getState());

        if (order.getOrderDetail() != null) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setId(order.getOrderDetail().getId());
            orderDetailDTO.setTotalPrice(order.getOrderDetail().getTotalPrice());
            orderDetailDTO.setPaymentType(order.getOrderDetail().getPaymentType());
            orderDetailDTO.setOrderDate(order.getOrderDetail().getOrderDate());

            List<OrderDetailArticleDTO> orderDetailArticleDTOs = order.getOrderDetail().getOrderDetailArticles().stream()
                    .map(this::convertToOrderDetailArticleDto)
                    .collect(Collectors.toList());

            // Log the articles
            orderDetailArticleDTOs.forEach(article -> {
                System.out.println("Article ID: " + article.getId());
                System.out.println("Article Quantity: " + article.getQuantity());
                System.out.println("Article Name: " + article.getArticle().getNameArticle());
            });

            orderDetailDTO.setOrderDetailArticles(orderDetailArticleDTOs);

            orderDTO.setOrderDetail(orderDetailDTO);
        }

        if (order.getClient() != null) {
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setEmail(order.getClient().getEmail());
            clientDTO.setName(order.getClient().getName());
            clientDTO.setSurname(order.getClient().getSurname());

            orderDTO.setClient(clientDTO);
        }

        return orderDTO;
    }


    private OrderDetailArticleDTO convertToOrderDetailArticleDto(OrderDetailArticle orderDetailArticle) {
        OrderDetailArticleDTO dto = new OrderDetailArticleDTO();
        dto.setId(orderDetailArticle.getId());
        dto.setQuantity(orderDetailArticle.getQuantity());

        if (orderDetailArticle.getArticle() != null) {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setIdArticle(orderDetailArticle.getArticle().getIdArticle());
            articleDTO.setNameArticle(orderDetailArticle.getArticle().getName());
            articleDTO.setDescription(orderDetailArticle.getArticle().getDescription());
            articleDTO.setAvailableQuantity(orderDetailArticle.getArticle().getAvailableQuantity());
            articleDTO.setPrice(orderDetailArticle.getArticle().getPrice());
            dto.setArticle(articleDTO);
        }

        return dto;
    }

    public <Entity, D> D convertToDTO(Entity entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }



}
