package com.pinpon.inventory.management.purchaseOrder.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class UpdateDTO {


    @Schema(example = "10", description = "stock of product")
    @Positive
    private Integer stock;

    @Schema(example = "100.99", description = "It is thje pruchase price of the product")
    @Positive
    private BigDecimal purchasePrice;
}
