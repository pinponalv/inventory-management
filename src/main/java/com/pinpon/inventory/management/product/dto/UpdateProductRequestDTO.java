package com.pinpon.inventory.management.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProductRequestDTO {
    @Schema(example = "Xbox 360", description = "This is name of product")
    private String name;
    @Schema(example = "This is a product for gaming", description = "This is description of product")
    private String description;
    @Schema(example = "300", description = "This is price of product")
    @Positive(message = "price must be greater than zero")
    private BigDecimal price;
}
