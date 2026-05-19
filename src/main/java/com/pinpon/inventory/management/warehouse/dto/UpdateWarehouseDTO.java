package com.pinpon.inventory.management.warehouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateWarehouseDTO {
    @Schema(example = "Almacen 1", description = "its the name of warehouse")
    private String name;
    @Schema(example = "BULEVAR 2000", description = "its the adress of warehouse")
    private String adress;
}
