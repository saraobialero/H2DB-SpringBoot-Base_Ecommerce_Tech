package com.ecommerce.model.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientDTO {
    int idClient;
    private String email;
    private String name;
    private String surname;


}
