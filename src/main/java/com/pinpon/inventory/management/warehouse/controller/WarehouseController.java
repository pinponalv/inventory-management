package com.pinpon.inventory.management.warehouse.controller;

import com.pinpon.inventory.management.warehouse.dto.CreateWarehouseDTO;
import com.pinpon.inventory.management.warehouse.dto.ResponseWarehouseDTO;
import com.pinpon.inventory.management.warehouse.dto.UpdateWarehouseDTO;
import com.pinpon.inventory.management.warehouse.service.IWarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping
    public ResponseEntity<ResponseWarehouseDTO> createWarehouse(@RequestBody CreateWarehouseDTO requestDTO) {
        ResponseWarehouseDTO response = warehouseService.createWarehouse(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "get all warehouses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping
    public ResponseEntity<List<ResponseWarehouseDTO>> getAllWarehouses() {
        List<ResponseWarehouseDTO> response = warehouseService.getWarehouses();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "get warehouse by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseWarehouseDTO> getWarehouseById(@Parameter(description = "Warehouse ID,", example = "1")
                                                                     @PathVariable Long id) {
        ResponseWarehouseDTO response = warehouseService.getWarehouseById(id);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "get warehouse by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseWarehouseDTO> getWarehouseByName(@Parameter(description = "Warehouse Name", example = "Juan")
                                                                       @PathVariable String name) {
        ResponseWarehouseDTO response = warehouseService.getWarehouseByName(name);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Update warehouse by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseWarehouseDTO> updateWarehouseById(@Parameter(description = "Warehouse ID", example = "1")
                                                                        @PathVariable Long id,
                                                                        @RequestBody UpdateWarehouseDTO requestDTO) {
        ResponseWarehouseDTO response = warehouseService.updateWarehouse(id, requestDTO);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Delete warehouse by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWarehouseDTO> deleteWarehouseById(@Parameter(description = "Warehouse ID", example = "1")
                                                                        @PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }

}
