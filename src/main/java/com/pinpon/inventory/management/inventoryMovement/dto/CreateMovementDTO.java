package com.pinpon.inventory.management.inventoryMovement.dto;

import com.pinpon.inventory.management.inventoryMovement.entity.enums.TypeMovement;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateMovementDTO {
    @Schema(example = "1", description = "Is the product ID")
    @NotNull(message = "productId is mandatory")
    private Long productId;
    @Schema(example = "1", description = "Is the warehouse ID")
    @NotNull(message = "warehouseId is mandatory")
    private Long warehouseId;
    @Schema(example = "1", description = "Is the user ID")
    @NotNull(message = "userId is mandatory")
    private Long userId;
    @Schema(example = "1", description = "Is the supplier ID")
    @NotNull(message = "supplierId is mandatory")
    private Long supplierId;

    @Schema(example = "10", description = "The quantity of stock that is moved")
    @NotNull(message = "the quantity is mandatory")
    @Positive
    private Integer quantity;
    @Schema(example = "IN / OUT", description = "The product is IN or OUT of warehouse")
    @NotNull(message = "the  type movement is mandatory")
    private TypeMovement typeMovement;
}
