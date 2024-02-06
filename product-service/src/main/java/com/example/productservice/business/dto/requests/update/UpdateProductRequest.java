package com.example.productservice.business.dto.requests.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {
    @NotBlank
    @Length(min = 2, max = 30,  message = "Ürün isminin karakter sayısı 2-30 aralığında olmalıdır.")
    private String name;

    @NotBlank
    @Length(min = 2, max = 16,  message = "Kategori isminin karakter sayısı 2-16 aralığında olmalıdır.")
    private String categoryName;

    @NotNull
    @Min(0)
    private int quantity;

    @NotNull
    @Min(1)
    private double unitPrice;
}
