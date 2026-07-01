package com.pinpon.inventory.management.purchaseOrder.controller;

import com.pinpon.inventory.management.purchaseOrder.dto.CreateDTO;
import com.pinpon.inventory.management.purchaseOrder.dto.ResponseDTO;
import com.pinpon.inventory.management.purchaseOrder.dto.UpdateDTO;
import com.pinpon.inventory.management.purchaseOrder.service.IPurchaseOrderService;
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
@RequestMapping("/api/purchaseOrder")
@Tag(name = "PurchaseOrder", description = "PurchaseOrder API Operations")
@PreAuthorize("hasAnyRole('ADMIN','MOD')")
@SecurityRequirement(name = "bearerAuth")
public class PurchaseOrderController {

    @Autowired
    private IPurchaseOrderService purchaseOrderService;


    @Operation(summary = "create a PurchaseOrder")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping("/user/{userId}/warehouse/{warehouseId}/product/{productId}/supplier/{supplierId}")
    public ResponseEntity<ResponseDTO> createPurchaseOrder(@Parameter(description = "User ID", example = "1")
                                                               @PathVariable Long userId, @Parameter(description = "Warehouse ID", example = "1")
    @PathVariable Long warehouseId, @Parameter(description = "Product ID", example = "1") @PathVariable Long productId,
                                                           @Parameter(description = "Supplier ID", example = "1")
                                                           @PathVariable Long supplierId, @RequestBody CreateDTO createDTO) {
        ResponseDTO response = purchaseOrderService.createPurchaseOrder(userId, warehouseId,productId, supplierId, createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get all PurchaseOrder")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping
    public ResponseEntity<List<ResponseDTO>> getAllPurchaseOrders(){
        List<ResponseDTO> allPurchaseOrders = purchaseOrderService.getPurchaseOrders();
        return ResponseEntity.status(HttpStatus.OK).body(allPurchaseOrders);

    }

    @Operation(summary = "Get PurchaseOrder by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getPurchaseOrderById(@Parameter(description = "PurchaseOrder ID", example = "1")
                                                                @PathVariable Long id){
        ResponseDTO response = purchaseOrderService.getPurchaseOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Update PurchaseOrder by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDTO> updatePurchaseOrder(@Parameter(description = "PurchaseOrder ID", example = "1")
                                                               @PathVariable Long id, @RequestBody UpdateDTO updateDTO){
        ResponseDTO response = purchaseOrderService.updatePurchaseOrder(id, updateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @Operation(summary = "Delete PurchaseOrder by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "PurchaseOrder deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletePurchaseOrder(@Parameter(description = "PurchaseOrder ID", example = "1")
                                                               @PathVariable Long id){
        purchaseOrderService.deletePurchaseOrder(id);
        return ResponseEntity.noContent().build();
    }
}
