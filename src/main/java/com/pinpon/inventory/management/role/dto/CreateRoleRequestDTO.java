package com.pinpon.inventory.management.role.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CreateRoleRequestDTO {
    @Schema(example = "ADMIN", description = "its the name of role of user")
    @NotBlank
    private String role;
    private Set<Long> permissions;
}
