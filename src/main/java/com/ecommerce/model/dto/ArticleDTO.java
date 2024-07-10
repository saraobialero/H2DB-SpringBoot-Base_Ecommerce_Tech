package com.ecommerce.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ArticleDTO {
    private int idArticle;
    private String nameArticle;
    private String feature;
    private String description;
    private int availableQuantity;
    private String imagePath;
    private BigDecimal price;
}
