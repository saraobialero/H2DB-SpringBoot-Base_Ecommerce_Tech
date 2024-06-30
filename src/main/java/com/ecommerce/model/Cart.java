package com.ecommerce.model;

import com.ecommerce.model.enums.PaymentType;
import com.ecommerce.model.enums.State;
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

    //MANY TO ONE FOR HISTORY
    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column (name = "payment_type")
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column (name = "state")
    private State state;

    @Column (name = "total_price")
    private double totalPrice;

    //RELATION MANY-TO-MANY: MORE ARTICLES FOR MORE CARTS
    @ManyToMany
    @JoinTable(
            name = "articles_has_carts",
            joinColumns = @JoinColumn(name="id_cart"),
            inverseJoinColumns = @JoinColumn(name="id_article")
    )
    private Set<Article> articles = new HashSet<>();




}
