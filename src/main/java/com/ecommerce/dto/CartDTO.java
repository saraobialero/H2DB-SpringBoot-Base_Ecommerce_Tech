package com.ecommerce.dto;

import com.ecommerce.model.Article;
import com.ecommerce.model.CartArticle;
import com.ecommerce.model.enums.PaymentType;
import com.ecommerce.model.enums.State;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class CartDTO {
    String idCart;
    private double totalPrice;
    private Set<CartArticleDTO> cartArticles = new HashSet<>();


}
