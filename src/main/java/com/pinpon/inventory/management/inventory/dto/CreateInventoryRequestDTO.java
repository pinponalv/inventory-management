package com.pinpon.inventory.management.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class  CreateInventoryRequestDTO {
    @Schema(example = "1", description = "Is the product ID")
    @NotNull(message = "productId is mandatory")
    private Long productId;
    @Schema(example = "1", description = "Is the warehouse ID")
    @NotNull(message = "warehouseId is mandatory")
    private Long warehouseId;
    @Schema(example = "10", description = "Stock of inventory")
    @NotNull(message = "the stock is mandatory")
    private Integer stock;
}
