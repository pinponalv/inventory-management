package com.pinpon.inventory.management.inventoryMovement.mapper;

import com.pinpon.inventory.management.inventoryMovement.dto.product.ProductDTO;
import com.pinpon.inventory.management.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(Product product);
}
