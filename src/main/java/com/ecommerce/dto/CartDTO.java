package com.ecommerce.dto;

import com.ecommerce.model.enums.State;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class CartDTO {
    int idCart;
    private double totalPrice;
    private Set<CartArticleDTO> cartArticles = new HashSet<>();


}
