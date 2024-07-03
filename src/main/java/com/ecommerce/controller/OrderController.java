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
                                                @PathVariable("idCart") int idCart) throws CartNotFoundException, OrderNotFoundException, ArticleNotFoundException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));
        Order order = orderFunctions.createOrder(idCart)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

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
