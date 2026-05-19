package com.pinpon.inventory.management.purchaseOrder.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateDTO {
    @Schema(example = "10", description = "stock of product")
    @NotNull
    @Positive
    private Integer stock;
    @Schema(example = "100.99", description = "It is the pruchase price of the product")
    @NotNull
    @Positive
    private BigDecimal purchasePrice;
}
