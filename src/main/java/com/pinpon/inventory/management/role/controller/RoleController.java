package com.pinpon.inventory.management.role.controller;

import com.pinpon.inventory.management.role.dto.CreateRoleRequestDTO;
import com.pinpon.inventory.management.role.dto.RoleResponseDTO;
import com.pinpon.inventory.management.role.dto.UpdateRoleRequestDTO;
import com.pinpon.inventory.management.role.service.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Role", description = "Role API Operations")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class RoleController {

    @Autowired
    private IRoleService roleService;


    @Operation(summary = "create a role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping
    public ResponseEntity<RoleResponseDTO> createRole(@Valid @RequestBody CreateRoleRequestDTO createRoleRequestDTO) {
        RoleResponseDTO role =  roleService.createRole(createRoleRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }

    @Operation(summary = "get all a roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {
        List<RoleResponseDTO> roles = roleService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }

    @Operation(summary = "get role by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> getRoleById(@Parameter(description = "Role ID", example = "1")
                                                           @PathVariable Long id) {
        RoleResponseDTO role = roleService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

    @Operation(summary = "Update role by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> updateRole(@Parameter(description = "Role id", example = "1")
                                                      @PathVariable Long id,
                                                      @Valid @RequestBody UpdateRoleRequestDTO updateRoleRequestDTO){
        RoleResponseDTO role = roleService.updateRole(id, updateRoleRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(role);

    }

    @Operation(summary = "Delete role by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@Parameter(description = "Role id", example = "1") @PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
