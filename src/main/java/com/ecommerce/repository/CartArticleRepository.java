package com.ecommerce.repository;

import com.ecommerce.model.CartArticleId;
import com.ecommerce.model.CartArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartArticleRepository extends JpaRepository<CartArticle, CartArticleId> {
}
