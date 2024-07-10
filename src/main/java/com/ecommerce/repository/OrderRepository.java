package com.ecommerce.repository;


import com.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.client.idClient = :idClient AND state != 'ACTIVE'")
    List<Order> findByIdClient(int idClient);

}
