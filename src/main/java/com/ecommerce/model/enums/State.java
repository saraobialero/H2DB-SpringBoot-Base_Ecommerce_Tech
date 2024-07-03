package com.ecommerce.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum State {
    ACTIVE,
    CLOSED,
    ANNULLED

}
