package com.ecommerce.model;

import com.ecommerce.model.enums.PaymentType;
import com.ecommerce.model.enums.State;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    int idOrder;

    @Enumerated(EnumType.STRING)
    @Column (name = "state")
    private State state;

    @Enumerated(EnumType.STRING)
    @Column (name = "payment_type")
    private PaymentType paymentType;

    //ONE-TO-ONE RELATIONSHIP
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Cart cart;

    //MANY-TO-ONE RELATIONSHIP: ONE CLIENT CAN HAVE MULTIPLE ORDERS
    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    private Client client;


}
