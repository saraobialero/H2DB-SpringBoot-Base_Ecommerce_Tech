package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "articles")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    int idArticle;

    @Column (name = "name")
    private String name;

    @Column (name = "description")
    private String description;

    @Column (name = "available_quantity")
    private int availableQuantity;

    @Column (name = "price")
    private double price;
}
