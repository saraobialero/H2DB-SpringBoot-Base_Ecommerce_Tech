package com.ecommerce.model.dto;

import com.ecommerce.model.enums.State;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {
    private int idOrder;
    private State state;
    private OrderDetailDTO orderDetail;
    private ClientDTO client;
}
