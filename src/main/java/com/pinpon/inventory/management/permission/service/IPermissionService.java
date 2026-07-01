package com.pinpon.inventory.management.permission.service;

import com.pinpon.inventory.management.permission.dto.CreatePermission;
import com.pinpon.inventory.management.permission.dto.PermissionResponse;
import com.pinpon.inventory.management.permission.dto.UpdatePermission;

import java.util.List;

public interface IPermissionService {
    PermissionResponse createPermission(CreatePermission createPermission);
    PermissionResponse updatePermission(Long id,UpdatePermission updatePermission);
    List<PermissionResponse> getAllPermissions();
    PermissionResponse getPermissionById(Long id);
    void deletePermission(Long id);
}