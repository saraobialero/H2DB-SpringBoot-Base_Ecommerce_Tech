package com.ecommerce.model;

import com.ecommerce.model.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private PaymentType paymentType;

}
