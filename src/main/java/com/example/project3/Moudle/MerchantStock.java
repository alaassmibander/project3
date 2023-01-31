package com.example.project3.Moudle;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotEmpty(message = "id is required")
    @Size(min = 3, message = "id has to be at least 3 Characters")
    private String id;

    @NotEmpty(message = "productId is required")
    @Size(min = 3, message = "id has to be at least 3 Characters")
    private String productId;

    @NotEmpty(message = "merchantId is required")
    @Size(min = 3, message = "id has to be at least 3 Characters")
    private String merchantId;

    @NotNull(message = "stock is required")
    @Min(value = 10, message = "has to be at least 10 in stock at the start")
    private int stock;

}