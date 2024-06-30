package com.ecommerce.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;


@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum PaymentType {

    NOT_DEFINED,
    CREDIT_CARD,
    GOOGLE_PAY,
    PAYPAL,
    BANK_TRANSFER

}


