package com.pinpon.inventory.management.inventoryMovement.mapper;

import com.pinpon.inventory.management.inventoryMovement.dto.user.UserDTO;
import com.pinpon.inventory.management.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
}
