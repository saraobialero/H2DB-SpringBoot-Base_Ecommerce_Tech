package com.ecommerce.model.dto;

import com.ecommerce.model.enums.PaymentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDetailDTO {
    private int id;
    private BigDecimal totalPrice;
    private PaymentType paymentType;
    private LocalDateTime orderDate;
    private List<OrderDetailArticleDTO> orderDetailArticles;
}
