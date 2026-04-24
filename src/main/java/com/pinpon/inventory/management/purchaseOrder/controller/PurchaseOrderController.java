package com.pinpon.inventory.management.purchaseOrder.controller;

import com.pinpon.inventory.management.purchaseOrder.dto.CreateDTO;
import com.pinpon.inventory.management.purchaseOrder.dto.ResponseDTO;
import com.pinpon.inventory.management.purchaseOrder.dto.UpdateDTO;
import com.pinpon.inventory.management.purchaseOrder.service.IPurchaseOrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchaseOrder")
@Tag(name = "PurchaseOrder", description = "PurchaseOrder API Operations")
public class PurchaseOrderController {

    @Autowired
    private IPurchaseOrderService purchaseOrderService;

    //Se crea la orden de compra y se le pasa el producto que quiero y el proveedor al que le compro
    //Para esto ya debo tener
    @PostMapping("/product/{productId}/supplier/{supplierId}")
    public ResponseEntity<ResponseDTO> createPurchaseOrder(@PathVariable Long productId, @PathVariable Long supplierId, CreateDTO createDTO) {
        ResponseDTO response = purchaseOrderService.createPurchaseOrder(productId, supplierId, createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseDTO>> getAllPurchaseOrders(){
        List<ResponseDTO> allPurchaseOrders = purchaseOrderService.getPurchaseOrders();
        return ResponseEntity.status(HttpStatus.OK).body(allPurchaseOrders);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getPurchaseOrderById(@PathVariable Long id){
        ResponseDTO response = purchaseOrderService.getPurchaseOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDTO> updatePurchaseOrder(@PathVariable Long id, @RequestBody UpdateDTO updateDTO){
        ResponseDTO response = purchaseOrderService.updatePurchaseOrder(id, updateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletePurchaseOrder(@PathVariable Long id){
        purchaseOrderService.deletePurchaseOrder(id);
        return ResponseEntity.noContent().build();
    }
}
