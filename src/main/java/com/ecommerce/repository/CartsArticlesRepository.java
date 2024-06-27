package com.ecommerce.repository;

import com.ecommerce.model.CartsArticlesId;
import com.ecommerce.model.CartsArticles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartsArticlesRepository extends JpaRepository<CartsArticles, CartsArticlesId> {
}
