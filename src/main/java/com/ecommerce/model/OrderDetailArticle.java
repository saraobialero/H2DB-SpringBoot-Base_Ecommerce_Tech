package com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_detail_articles")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderDetailArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order_detail", referencedColumnName = "id", nullable = false)
    private OrderDetail orderDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_article", referencedColumnName = "id", nullable = false)
    private Article article;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity); // Non includere orderDetail o article
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetailArticle)) return false;
        OrderDetailArticle that = (OrderDetailArticle) o;
        return Objects.equals(id, that.id) && Objects.equals(quantity, that.quantity);
    }
}
