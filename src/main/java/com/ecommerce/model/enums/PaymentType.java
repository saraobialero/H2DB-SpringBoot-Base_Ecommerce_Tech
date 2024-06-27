package com.ecommerce.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum PaymentType {
    CREDIT_CARD,
    GOOGLE_PAY,
    PAYPAL,
    BANK_TRANSFER
}
