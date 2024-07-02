package com.ecommerce.dto;

import com.ecommerce.model.Client;
import com.ecommerce.model.enums.PaymentType;
import com.ecommerce.model.enums.State;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {
    private int idOrder;
    private State state;
    private PaymentType paymentType;
    private CartDTO cart;
   // private ClientDTO client; TODO:ADD?
}
