package com.pinpon.inventory.management.inventoryMovement.mapper;

import com.pinpon.inventory.management.inventoryMovement.dto.warehouse.WarehouseDTO;
import com.pinpon.inventory.management.warehouse.entity.Warehouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    WarehouseDTO toDTO(Warehouse warehouse);
}
