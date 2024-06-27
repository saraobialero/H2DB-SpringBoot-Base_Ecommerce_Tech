package com.ecommerce.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CartsArticlesId implements Serializable {
    private String idCart;
    private String idArticle;
}
