package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client implements Serializable {
    @Id
    @Column(name= "id_client")
    String idClient;

    @Column (name = "name")
    private String name;

    @Column (name = "surname")
    private String surname;

    @Column (name = "password")
    private String password;

    //RELATION ONE-TO-ONE: ONE CLIENT CAN HAVE ONE CART
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Cart cart;

}
