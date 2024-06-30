package com.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "articles")
public class Article implements Serializable {

    @Id
    @Column(name= "id_article")
    String idArticle;

    @Column (name = "name_article")
    private String nameArticle;

    @Column (name = "description")
    private String description;

    @Column (name = "available_quantity")
    private int availableQuantity;

    @Column (name = "price")
    private double price;
}
