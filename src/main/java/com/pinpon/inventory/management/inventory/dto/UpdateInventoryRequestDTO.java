package com.pinpon.inventory.management.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateInventoryRequestDTO {
    @Schema(example = "1", description = "Is the product ID")
    private Long productId;
    @Schema(example = "1", description = "Is the warehouse ID")
    private Long warehouseId;
    @Schema(example = "10",description = "Stock of inventory")
    private int stock;
}
