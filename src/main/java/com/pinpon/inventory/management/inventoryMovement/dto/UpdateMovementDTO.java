package com.pinpon.inventory.management.inventoryMovement.dto;

import com.pinpon.inventory.management.inventoryMovement.entity.enums.TypeMovement;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateMovementDTO {
    /*
    *
    *
    * private Long productId;
    private Long warehouseId;
    private Long userId;
    private Long supplierId;
    *
    * */

    @Schema(example = "1", description = "The quantity of stock that is moved")
    @Positive
    private Integer quantity;
    @Schema(example = "IN / OUT", description = "The product is IN or OUT of warehouse")
    private TypeMovement typeMovement;
}
