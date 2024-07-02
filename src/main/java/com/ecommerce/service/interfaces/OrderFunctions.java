package com.ecommerce.service.interfaces;

import com.ecommerce.model.enums.PaymentType;

public interface OrderFunctions {
    boolean payOrder(int idOrder, PaymentType paymentType);
}
