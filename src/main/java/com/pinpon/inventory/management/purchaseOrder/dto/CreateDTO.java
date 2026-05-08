package com.pinpon.inventory.management.purchaseOrder.dto;

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
    @NotNull
    @Positive
    private Integer stock;
    @NotNull
    @Positive
    private BigDecimal purchasePrice;
}
