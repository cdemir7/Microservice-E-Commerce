package com.example.customerservice.business.dto.requests.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
    @NotBlank
    @Length(min = 6, max = 50, message = "İsim 6-50 karakter arası uzunlukta olmalıdır.")
    private String customerName;

    @NotBlank
    @Length(min = 10, max = 10, message = "Telefon numarası 10 haneli olmalı ve 5 ile başlamalıdır.")
    private String phoneNumber;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    private String mailAddress;

    @NotBlank
    private String address;
}


