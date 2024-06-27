package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "carts_articles")
@IdClass(CartsArticlesId.class)
public class CartsArticles implements Serializable {
    @Id
    @Column(name= "id_cart")
    String idCart;

    @Id
    @Column(name= "id_article")
    String idArticle;

    @Column (name = "quantity")
    private int quantity ;


}
