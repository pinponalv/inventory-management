package com.pinpon.inventory.management.warehouse.controller;

import com.pinpon.inventory.management.warehouse.dto.CreateWarehouseDTO;
import com.pinpon.inventory.management.warehouse.dto.ResponseWarehouseDTO;
import com.pinpon.inventory.management.warehouse.dto.UpdateWarehouseDTO;
import com.pinpon.inventory.management.warehouse.service.IWarehouseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
@Tag(name = "Warehouse", description = "Warehouse API Operations")
public class WarehouseController {
    @Autowired
    private IWarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<ResponseWarehouseDTO> createWarehouse(@RequestBody CreateWarehouseDTO requestDTO) {
        ResponseWarehouseDTO response = warehouseService.createWarehouse(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseWarehouseDTO>> getAllWarehouses() {
        List<ResponseWarehouseDTO> response = warehouseService.getWarehouses();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWarehouseDTO> getWarehouseById(@PathVariable Long id) {
        ResponseWarehouseDTO response = warehouseService.getWarehouseById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseWarehouseDTO> getWarehouseByName(@PathVariable String name) {
        ResponseWarehouseDTO response = warehouseService.getWarehouseByName(name);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseWarehouseDTO> updateWarehouseById(@PathVariable Long id, @RequestBody UpdateWarehouseDTO requestDTO) {
        ResponseWarehouseDTO response = warehouseService.updateWarehouse(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWarehouseDTO> deleteWarehouseById(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }

}
