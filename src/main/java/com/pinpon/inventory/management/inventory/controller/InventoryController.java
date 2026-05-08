package com.pinpon.inventory.management.inventory.controller;

import com.pinpon.inventory.management.inventory.dto.CreateInventoryRequestDTO;
import com.pinpon.inventory.management.inventory.dto.ResponseInventoryDTO;
import com.pinpon.inventory.management.inventory.dto.UpdateInventoryRequestDTO;
import com.pinpon.inventory.management.inventory.service.IInventoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@Tag(name = "Inventory", description = "Inventory API Operations")
public class InventoryController {
    @Autowired
    private IInventoryService inventoryService;

    @PostMapping
    public ResponseEntity<ResponseInventoryDTO> createInventory(@Valid @RequestBody CreateInventoryRequestDTO requestDTO) {
        ResponseInventoryDTO response = inventoryService.createInventory(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseInventoryDTO>> getAllInventory() {
        List<ResponseInventoryDTO> response = inventoryService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseInventoryDTO> getInventoryById(@PathVariable Long id) {
        ResponseInventoryDTO response = inventoryService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseInventoryDTO> updateInventory(@PathVariable Long id
            ,@Valid @RequestBody UpdateInventoryRequestDTO requestDTO) {
        ResponseInventoryDTO response = inventoryService.updateInventory(id, requestDTO);
        return  ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseInventoryDTO> deleteInventoryById(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/product/{productId}/warehouse/{warehouseId}")
    public ResponseEntity<ResponseInventoryDTO> getInventoryByProductIdAndWarehouseId(@PathVariable Long productId
            , @PathVariable Long warehouseId) {
        ResponseInventoryDTO response = inventoryService.findByProductIdAndWarehouseId(productId, warehouseId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/productName/{productName}")
    public ResponseEntity<ResponseInventoryDTO> getInventoryByProductName(@PathVariable String productName) {
        ResponseInventoryDTO response = inventoryService.findByProductName(productName);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/warehouseName/{warehouseName}")
    public ResponseEntity<ResponseInventoryDTO> getInventoryByWarehouseName(@PathVariable String warehouseName) {
        ResponseInventoryDTO response = inventoryService.findByWarehouseName(warehouseName);
        return ResponseEntity.ok(response);
    }
}
