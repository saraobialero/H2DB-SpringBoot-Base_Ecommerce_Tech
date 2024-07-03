package com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JsonManagedReference
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orders;

    @Override
    public int hashCode() {
        return Objects.hash(idClient, email, name, surname); // Non includere carts o orders
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(idClient, client.idClient) &&
                Objects.equals(email, client.email) &&
                Objects.equals(name, client.name) &&
                Objects.equals(surname, client.surname);
    }


}
