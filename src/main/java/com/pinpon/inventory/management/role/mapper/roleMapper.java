package com.pinpon.inventory.management.role.mapper;

import com.pinpon.inventory.management.role.dto.RoleResponseDTO;
import com.pinpon.inventory.management.role.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface roleMapper {
    RoleResponseDTO roleToRoleResponseDTO(Role role);
}
