package com.pinpon.inventory.management.inventoryMovement.dto;

import com.pinpon.inventory.management.inventoryMovement.entity.enums.TypeMovement;
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

    @Positive
    private Integer quantity;

    @NotNull
    private TypeMovement typeMovement;
}
