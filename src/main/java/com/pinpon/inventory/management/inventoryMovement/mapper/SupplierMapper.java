package com.pinpon.inventory.management.inventoryMovement.mapper;

import com.pinpon.inventory.management.inventoryMovement.dto.supplier.SupplierDTO;
import com.pinpon.inventory.management.supplier.entity.Supplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierDTO toDTO(Supplier supplier);

}
