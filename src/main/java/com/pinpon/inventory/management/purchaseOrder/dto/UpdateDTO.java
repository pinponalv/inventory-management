package com.pinpon.inventory.management.purchaseOrder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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

    @NotBlank
    @PositiveOrZero
    private int stock;

    @NotBlank
    @Positive
    private BigDecimal purchasePrice;
}
