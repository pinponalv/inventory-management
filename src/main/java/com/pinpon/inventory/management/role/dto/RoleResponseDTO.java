package com.pinpon.inventory.management.role.dto;

import com.pinpon.inventory.management.permission.dto.PermissionResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleResponseDTO {
    private Long id;
    private String role;
    private Set<PermissionResponse> permissions;

}
