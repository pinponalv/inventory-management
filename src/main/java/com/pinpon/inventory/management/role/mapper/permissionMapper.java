package com.pinpon.inventory.management.role.mapper;

import com.pinpon.inventory.management.permission.dto.PermissionResponse;
import com.pinpon.inventory.management.permission.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface permissionMapper {
    PermissionResponse toDTO(Permission permission);
}
