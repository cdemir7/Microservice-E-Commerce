package com.example.customerservice.business.dto.responses.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerResponse {
    private UUID id;
    private String customerName;
    private String phoneNumber;
    private String mailAddress;
    private String address;
}

