package com.ecommerce.dto;

import com.ecommerce.model.enums.PaymentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDetailDTO {
    private int id;
    private double totalPrice;
    private PaymentType paymentType;
    private LocalDateTime orderDate;
    private List<OrderDetailArticleDTO> orderDetailArticles;
}
