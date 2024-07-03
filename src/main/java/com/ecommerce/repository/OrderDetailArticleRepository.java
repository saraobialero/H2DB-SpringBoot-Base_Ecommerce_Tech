package com.ecommerce.repository;

import com.ecommerce.model.OrderDetailArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailArticleRepository extends JpaRepository<OrderDetailArticle, Integer> {
}
