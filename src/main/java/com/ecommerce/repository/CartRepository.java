package com.ecommerce.repository;

import com.ecommerce.model.Cart;
import com.ecommerce.model.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {

    @Query("SELECT c FROM Cart c WHERE c.client.idClient = :idClient")
    List<Cart> findAllByClientId(@Param("idClient") String idClient);

    @Query("SELECT c FROM Cart c WHERE c.idCart = :idCart")
    Optional<Cart> findByIdCart(@Param("idCart") String idCart);

    @Query("SELECT c FROM Cart c WHERE c.client.idClient = :idClient AND c.state != :state")
    Optional<Cart> findActiveCartByClientIdAndStateNot(@Param("idClient") String idClient, @Param("state") State state);

    @Query("SELECT c FROM Cart c WHERE c.idCart = :idCart AND c.state != :state")
    Optional<Cart> findActiveCartByCartIdAndStateNot(@Param("idCart") String idCart, @Param("state") State state);


}
