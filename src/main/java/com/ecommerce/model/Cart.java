package com.ecommerce.model;

import com.ecommerce.model.enums.PaymentType;
import com.ecommerce.model.enums.State;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "carts")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    int idCart;

    //MANY-TO-ONE RELATIONSHIP:
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    private Client client;


    @Column (name = "total_price")
    private double totalPrice;

    //RELATION MANY-TO-MANY: MORE ARTICLES FOR MORE CARTS
    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "articles_has_carts",
            joinColumns = @JoinColumn(name="id_cart"),
            inverseJoinColumns = @JoinColumn(name="id_article")
    )
    private Set<Article> articles = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(idCart, totalPrice); // Non includere client
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(idCart, cart.getIdCart()) &&
                Objects.equals(totalPrice, cart.totalPrice);
    }





}
