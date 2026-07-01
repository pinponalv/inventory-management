package com.pinpon.inventory.management.inventoryMovement.controller;

import com.pinpon.inventory.management.inventoryMovement.dto.CreateMovementDTO;
import com.pinpon.inventory.management.inventoryMovement.dto.ResponseMovementDTO;
import com.pinpon.inventory.management.inventoryMovement.dto.UpdateMovementDTO;
import com.pinpon.inventory.management.inventoryMovement.service.IMovementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movements")
@Tag(name = "InventoryMovement", description = "InventoryMovement API Operations")
@PreAuthorize("hasAnyRole('ADMIN','MOD')")
@SecurityRequirement(name = "bearerAuth")
public class InventoryMovementController {

    @Autowired
    private IMovementService movementService;

    /**
    @PostMapping("/produt/{productId}/warehouse/{warehouseId}/user/{userId}/supplier/{supplierId}")
    public ResponseEntity<ResponseMovementDTO> createMovement(@PathVariable Long productId
            , @PathVariable Long warehouseId, @PathVariable Long userId
            , @PathVariable Long supplierId, @RequestBody CreateMovementDTO requestDTO){


        ResponseMovementDTO response = movementService.createMovement(productId, warehouseId, userId, supplierId, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } **/

    @Operation(summary = "Get all movements")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping
    public ResponseEntity<List<ResponseMovementDTO>> getAllMovements(){
        List<ResponseMovementDTO> response = movementService.findAllMovement();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get movements by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMovementDTO> getMovementById(@Parameter(description = "Movement ID", example = "1")
                                                                   @PathVariable Long id){
        ResponseMovementDTO response = movementService.findMovementById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update movements by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseMovementDTO> updateMovement(@Parameter(description = "Movement ID", example = "1")
                                                                  @PathVariable Long id,
                                                              @RequestBody UpdateMovementDTO requestDTO){
        ResponseMovementDTO response = movementService.updateMovement(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete movement by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movement deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMovementDTO> deleteMovement(@Parameter(description = "Movement ID", example = "1")
                                                                  @PathVariable Long id){
        movementService.deleteMovement(id);
        return ResponseEntity.noContent().build();
    }

    //TODO: ADD SEARCH BY USERNAME
}
