package com.pinpon.inventory.management.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProductRequestDTO {
    @Schema(example = "Xbox 360", description = "This is name of product")
    @NotBlank(message = "The name cannot be empty")
    @Size(min = 1, max = 200)
    private String name;
    @Schema(example = "This is a product for gaming", description = "This is description of product")
    @NotBlank(message = "The description cannot be empty")
    @Size(min = 1, max = 200)
    private String description;
    @Schema(example = "300", description = "This is price of product")
    @NotNull(message = "Price of product is mandatory")
    @Positive
    private BigDecimal price;
}
