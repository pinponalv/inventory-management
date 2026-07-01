package com.pinpon.inventory.management.user.dto;

import com.pinpon.inventory.management.role.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserResponseDTO {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Set<Role> roles;
}
