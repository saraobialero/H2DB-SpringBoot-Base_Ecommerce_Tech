package com.ecommerce.repository;

import com.ecommerce.model.Cart;
import com.ecommerce.model.enums.State;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT c FROM Cart c WHERE c.client.idClient = :idClient")
    List<Cart> findAllByClientId(@Param("idClient") int idClient);

    Optional<Cart> findByIdCart(int idCart);

    @Modifying
    @Transactional
    @Query(value= "delete from carts where id = :idCart AND id_client = :idClient", nativeQuery = true)
    void deleteCartById(int idCart, int idClient);

    @Query("SELECT c FROM Cart c WHERE c.client.idClient = :idClient")
    Optional<Cart> findByIdClient(@Param("idClient") int idClient);



}
