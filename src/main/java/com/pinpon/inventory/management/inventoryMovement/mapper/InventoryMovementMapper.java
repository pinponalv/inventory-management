package com.pinpon.inventory.management.inventoryMovement.mapper;

import com.pinpon.inventory.management.inventoryMovement.dto.ResponseMovementDTO;
import com.pinpon.inventory.management.inventoryMovement.entity.InventoryMovement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//,
//uses = {ProductMapper.class, SupplierMapper.class,
//UserMapper.class, WarehouseMapper.class}

@Mapper(componentModel = "spring")
public interface InventoryMovementMapper {

    //Toma el valor de producto.id y asignalo a productId
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "warehouse.id", target = "warehouseId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "supplier.id", target = "supplierId")
    ResponseMovementDTO toDTO(InventoryMovement inventoryMovement);
}
