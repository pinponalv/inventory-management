package com.pinpon.inventory.management.permission.service;

import com.pinpon.inventory.management.permission.dto.CreatePermission;
import com.pinpon.inventory.management.permission.dto.PermissionResponse;
import com.pinpon.inventory.management.permission.dto.UpdatePermission;
import com.pinpon.inventory.management.permission.entity.Permission;
import com.pinpon.inventory.management.permission.repository.IPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService implements  IPermissionService{
    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    public PermissionResponse createPermission(CreatePermission createPermission) {
        Permission permision = new Permission();
        permision.setPermissionName(createPermission.getPermissionName());
        Permission permissionSave = permissionRepository.save(permision);
        return new PermissionResponse(
                permissionSave.getId(),
                permissionSave.getPermissionName()
        );
    }

    @Override
    public PermissionResponse updatePermission(Long id, UpdatePermission updatePermission) {
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission not found"));
        permission.setPermissionName(updatePermission.getPermissionName());
        Permission permissionSave = permissionRepository.save(permission);
        return new PermissionResponse(
                permissionSave.getId(),
                permissionSave.getPermissionName()
        );
    }

    @Override
    public List<PermissionResponse> getAllPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        List<PermissionResponse> permissionResponses = new ArrayList<>();
        for(Permission permission : permissions){
            PermissionResponse permissionResponse = new PermissionResponse(
                    permission.getId(),
                    permission.getPermissionName()
            );
            permissionResponses.add(permissionResponse);
        }
        return permissionResponses;
    }

    @Override
    public PermissionResponse getPermissionById(Long id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission not found"));
        return new PermissionResponse(
                permission.getId(),
                permission.getPermissionName()
        );
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
}
