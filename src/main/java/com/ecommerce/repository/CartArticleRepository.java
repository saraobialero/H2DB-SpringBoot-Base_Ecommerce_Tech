package com.ecommerce.repository;

import com.ecommerce.model.CartArticleId;
import com.ecommerce.model.CartArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartArticleRepository extends JpaRepository<CartArticle, CartArticleId> {

    @Query("SELECT ca FROM CartArticle ca WHERE ca.id.idCart = :idCart")
    List<CartArticle> findByIdCart(@Param("idCart") int idCart);
}
