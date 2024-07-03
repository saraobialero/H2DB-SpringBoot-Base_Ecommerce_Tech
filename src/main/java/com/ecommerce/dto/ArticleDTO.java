package com.ecommerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleDTO {
    private int idArticle;
    private String nameArticle;
    private String description;
    private int availableQuantity;
    private double price;
}
