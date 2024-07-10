package com.ecommerce.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailArticleDTO {
    private int id;
    private int quantity;
    private ArticleDTO article;
}
