package com.ecommerce.dto;

import lombok.Data;

@Data
public class CartArticleDTO {
   private int quantity;
   private ArticleDTO article;
}
