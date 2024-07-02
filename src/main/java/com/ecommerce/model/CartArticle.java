package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "articles_has_carts")
public class CartArticle implements Serializable {

    @EmbeddedId
    private CartArticleId id;

    @Column (name = "quantity")
    private int quantity ;

    }

