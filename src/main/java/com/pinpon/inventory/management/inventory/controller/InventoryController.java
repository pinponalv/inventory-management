package com.pinpon.inventory.management.inventory.controller;

import com.pinpon.inventory.management.inventory.dto.CreateInventoryRequestDTO;
import com.pinpon.inventory.management.inventory.dto.ResponseInventoryDTO;
import com.pinpon.inventory.management.inventory.dto.UpdateInventoryRequestDTO;
import com.pinpon.inventory.management.inventory.service.IInventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "create a inventory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping
    public ResponseEntity<ResponseInventoryDTO> createInventory(@Valid @RequestBody CreateInventoryRequestDTO requestDTO) {
        ResponseInventoryDTO response = inventoryService.createInventory(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get all inventorys")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping
    public ResponseEntity<List<ResponseInventoryDTO>> getAllInventory() {
        List<ResponseInventoryDTO> response = inventoryService.findAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get inventory by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseInventoryDTO> getInventoryById(@Parameter(description = "Inventory ID", example = "1")
                                                                     @PathVariable Long id) {
        ResponseInventoryDTO response = inventoryService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update inventory by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseInventoryDTO> updateInventory(@Parameter(description = "Inventory ID", example = "1")
                                                                    @PathVariable Long id
            ,@Valid @RequestBody UpdateInventoryRequestDTO requestDTO) {
        ResponseInventoryDTO response = inventoryService.updateInventory(id, requestDTO);
        return  ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseInventoryDTO> deleteInventoryById(@Parameter(description = "Inventory ID", example = "1")
                                                                        @PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get inventory by product id and warehouse id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/product/{productId}/warehouse/{warehouseId}")
    public ResponseEntity<ResponseInventoryDTO> getInventoryByProductIdAndWarehouseId(@Parameter(description = "Product ID", example = "1")
                                                                                          @PathVariable Long productId,
                                                                                      @Parameter(description = "Warehouse ID", example = "1")
                                                                                      @PathVariable Long warehouseId) {
        ResponseInventoryDTO response = inventoryService.findByProductIdAndWarehouseId(productId, warehouseId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get inventory by product name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/productName/{productName}")
    public ResponseEntity<ResponseInventoryDTO> getInventoryByProductName(@Parameter(description = "Product name", example = "iphone 14")
                                                                              @PathVariable String productName) {
        ResponseInventoryDTO response = inventoryService.findByProductName(productName);
        return ResponseEntity.ok(response);

    }

    @Operation(summary = "Get inventory by warehouse name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Inventory deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/warehouseName/{warehouseName}")
    public ResponseEntity<ResponseInventoryDTO> getInventoryByWarehouseName(@Parameter(description = "Warehouse name", example = "warehouseName")
                                                                                @PathVariable String warehouseName) {
        ResponseInventoryDTO response = inventoryService.findByWarehouseName(warehouseName);
        return ResponseEntity.ok(response);
    }
}
