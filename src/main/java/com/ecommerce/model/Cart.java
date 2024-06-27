package com.ecommerce.model;

import com.ecommerce.model.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "carts")
public class Cart implements Serializable {
    @Id
    @Column(name= "id_cart")
    String idCart;

    //RELATION ONE-TO-ONE: SINGLE CART FOR CLIENT
    @OneToOne
    @JoinColumn (name = "id_client", referencedColumnName = "id_client")
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column (name = "payment_type")
    private PaymentType paymentType;

    @Column (name = "total_price")
    private int totalPrice;

    //RELATION MANY-TO-MANY: MORE ARTICLES FOR MORE CARTS
    @ManyToMany
    @JoinTable(
            name = "articles_has_carts",
            joinColumns = @JoinColumn(name="id_cart"),
            inverseJoinColumns = @JoinColumn(name="id_article")
    )
    private Set<Article> articles = new HashSet<>();



}
