package com.pinpon.inventory.management.purchaseOrder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDTO {
    private Long id;
    private String productName;
    private String supplierName;
    private int stock;
    private BigDecimal purchasePrice;
}
