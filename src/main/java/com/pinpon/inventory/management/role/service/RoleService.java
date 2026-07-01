package com.pinpon.inventory.management.role.service;

import com.pinpon.inventory.management.permission.dto.PermissionResponse;
import com.pinpon.inventory.management.permission.entity.Permission;
import com.pinpon.inventory.management.permission.repository.IPermissionRepository;
import com.pinpon.inventory.management.role.dto.CreateRoleRequestDTO;
import com.pinpon.inventory.management.role.dto.RoleResponseDTO;
import com.pinpon.inventory.management.role.dto.UpdateRoleRequestDTO;
import com.pinpon.inventory.management.role.entity.Role;
import com.pinpon.inventory.management.role.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    IPermissionRepository permissionRepository;

    @Override
    public RoleResponseDTO createRole(CreateRoleRequestDTO createRoleRequestDTO) {
        Set<Permission> permissionList = new HashSet<>();
        Permission readPermission;

        for(Long permissionId : createRoleRequestDTO.getPermissions()){
            readPermission = permissionRepository.findById(permissionId).orElseThrow(() -> new RuntimeException("Permission not found"));
            if(readPermission != null){
                permissionList.add(readPermission);
            }
        }


        Role role = new Role();
        role.setRole(createRoleRequestDTO.getRole());
        role.setPermissionList(permissionList);

        Role roleSave = roleRepository.save(role);

        Set<PermissionResponse> permissionResponseList = new HashSet<>();

        for(Permission permission : roleSave.getPermissionList()){
            PermissionResponse dto = new PermissionResponse();
            dto.setId(permission.getId());
            dto.setPermissionName(permission.getPermissionName());

            permissionResponseList.add(dto);
        }

        RoleResponseDTO response  = new RoleResponseDTO();
        response.setId(roleSave.getId());
        response.setRole(roleSave.getRole());
        response.setPermissions(permissionResponseList);

        return response;
    }

    @Override
    public List<RoleResponseDTO> findAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleResponseDTO> roleResponseDTOS = new ArrayList<>();

        for (Role role : roles) {
            Set<PermissionResponse> permissionResponseList = new HashSet<>();

            for(Permission permission : role.getPermissionList()){
                PermissionResponse dto = new PermissionResponse();
                dto.setId(permission.getId());
                dto.setPermissionName(permission.getPermissionName());
                permissionResponseList.add(dto);
            }

            RoleResponseDTO roleDTO = new RoleResponseDTO();
            roleDTO.setId(role.getId());
            roleDTO.setRole(role.getRole());
            roleDTO.setPermissions(permissionResponseList);

            roleResponseDTOS.add(roleDTO);
        }
        return roleResponseDTOS;
    }

    @Override
    public RoleResponseDTO findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        Set<PermissionResponse> permissionResponseList = new HashSet<>();

        for(Permission permission : role.getPermissionList()){
            PermissionResponse dto = new PermissionResponse();
            dto.setId(permission.getId());
            dto.setPermissionName(permission.getPermissionName());
            permissionResponseList.add(dto);
        }

        RoleResponseDTO roleDTO = new RoleResponseDTO();
        roleDTO.setId(role.getId());
        roleDTO.setRole(role.getRole());
        roleDTO.setPermissions(permissionResponseList);

        return roleDTO;
    }

    @Override
    public RoleResponseDTO updateRole(Long id,UpdateRoleRequestDTO updateRoleRequestDTO) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));

        if (updateRoleRequestDTO.getRole() != null) {
            role.setRole(updateRoleRequestDTO.getRole());
        }

        Role saved = roleRepository.save(role);

        Set<PermissionResponse> permissionResponseList = new HashSet<>();

        for(Permission permission : saved.getPermissionList()){
            PermissionResponse dto = new PermissionResponse();
            dto.setId(permission.getId());
            dto.setPermissionName(permission.getPermissionName());
            permissionResponseList.add(dto);
        }

        RoleResponseDTO roleDTO = new RoleResponseDTO();
        roleDTO.setId(role.getId());
        roleDTO.setRole(role.getRole());
        roleDTO.setPermissions(permissionResponseList);
        return roleDTO;
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
