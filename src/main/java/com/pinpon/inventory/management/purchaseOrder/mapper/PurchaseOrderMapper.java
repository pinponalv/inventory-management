package com.pinpon.inventory.management.purchaseOrder.mapper;

import com.pinpon.inventory.management.purchaseOrder.dto.ResponseDTO;
import com.pinpon.inventory.management.purchaseOrder.entity.PurchaseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {
    @Mapping(source = "products.name", target = "productName")
    @Mapping(source = "supplier.name", target = "supplierName")
    ResponseDTO toDTO(PurchaseOrder purchaseOrder);
}
