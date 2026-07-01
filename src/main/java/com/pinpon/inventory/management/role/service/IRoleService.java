package com.pinpon.inventory.management.role.service;

import com.pinpon.inventory.management.role.dto.CreateRoleRequestDTO;
import com.pinpon.inventory.management.role.dto.RoleResponseDTO;
import com.pinpon.inventory.management.role.dto.UpdateRoleRequestDTO;

import java.util.List;

public interface IRoleService {
    RoleResponseDTO createRole(CreateRoleRequestDTO createRoleRequestDTO);
    List<RoleResponseDTO> findAll();
    RoleResponseDTO findById(Long id);
    RoleResponseDTO updateRole(Long id,UpdateRoleRequestDTO updateRoleRequestDTO);
    void deleteRole(Long id);
}
