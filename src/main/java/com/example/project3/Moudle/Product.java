package com.example.project3.Moudle;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    @NotEmpty(message = "id is required")
    @Size(min = 3, message = "id has to be at least 3 Characters")
    private String id;

    @NotEmpty(message = "name is required")
    @Size(min = 3, message = "name has to be at least 3 Characters")
    private String name;

    @NotNull(message = "price is required")
    @PositiveOrZero(message = "Price has to be a positive number")
    private double price;

    @NotNull(message = "price is required")
    @Size(min = 3, message = "categoryID has to be at least 3 Characters")
    private String categoryID;

}