package com.ecommerce.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class CartDTO {
    int idCart;
    private BigDecimal totalPrice;
    private Set<CartArticleDTO> cartArticles = new HashSet<>();


}
