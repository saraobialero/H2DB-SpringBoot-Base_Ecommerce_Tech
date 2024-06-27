package com.ecommerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleDTO {
    String idArticle;
    private String nameArticle;
    private String description;
    private int quantity ;
    private double price;
}
