package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id")
    int idClient;

    @Column (name = "name")
    private String name;

    @Column (name = "email")
    private String email;

    @Column (name = "surname")
    private String surname;

    @Column (name = "password")
    private String password;

    //ONE-TO-ONE RELATIONSHIP: ONE CLIENT CAN HAVE ONE CART
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Cart cart;

    // RELATION ONE-TO-MANY: ONE CLIENT CAN HAVE MULTIPLE ORDERS
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orders;

}
