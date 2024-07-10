package com.ecommerce.repository;

import com.ecommerce.model.OrderDetailArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailArticleRepository extends JpaRepository<OrderDetailArticle, Integer> {
}
