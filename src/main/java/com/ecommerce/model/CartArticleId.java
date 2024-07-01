package com.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CartArticleId implements Serializable {
    @Column(name = "id_cart")
    private int idCart;

    @Column(name = "id_article")
    private int idArticle;

}
