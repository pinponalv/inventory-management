package com.pinpon.inventory.management.inventoryMovement.controller;

import com.pinpon.inventory.management.inventoryMovement.dto.CreateMovementDTO;
import com.pinpon.inventory.management.inventoryMovement.dto.ResponseMovementDTO;
import com.pinpon.inventory.management.inventoryMovement.dto.UpdateMovementDTO;
import com.pinpon.inventory.management.inventoryMovement.service.IMovementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movements")
@Tag(name = "InventoryMovement", description = "InventoryMovement API Operations")
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

    @GetMapping
    public ResponseEntity<List<ResponseMovementDTO>> getAllMovements(){
        List<ResponseMovementDTO> response = movementService.findAllMovement();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMovementDTO> getMovementById(@PathVariable Long id){
        ResponseMovementDTO response = movementService.findMovementById(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseMovementDTO> updateMovement(@PathVariable Long id, @RequestBody UpdateMovementDTO requestDTO){
        ResponseMovementDTO response = movementService.updateMovement(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMovementDTO> deleteMovement(@PathVariable Long id){
        movementService.deleteMovement(id);
        return ResponseEntity.noContent().build();
    }

    //TODO: ADD SEARCH BY USERNAME
}
