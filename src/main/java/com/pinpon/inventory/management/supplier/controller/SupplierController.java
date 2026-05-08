package com.pinpon.inventory.management.supplier.controller;

import com.pinpon.inventory.management.supplier.dto.CreateSupplierRequestDTO;
import com.pinpon.inventory.management.supplier.dto.SupplierResponseDTO;
import com.pinpon.inventory.management.supplier.dto.UpdateSupplierRequestDTO;
import com.pinpon.inventory.management.supplier.service.ISupplierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@Tag(name = "Supplier", description = "Supplier API Operations")
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> createSupplier(@RequestBody CreateSupplierRequestDTO requestDTO) {
        SupplierResponseDTO supplierResponseDTO = supplierService.createSupplier(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers() {
        List<SupplierResponseDTO> response = supplierService.findAllSuppliers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getSupplier(@PathVariable Long id) {
        SupplierResponseDTO response = supplierService.findSupplierById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<SupplierResponseDTO> getSupplierByEmail(@PathVariable String email) {
        SupplierResponseDTO response = supplierService.findSupplierByEmail(email);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> updateSupplier(@PathVariable Long id, @RequestBody UpdateSupplierRequestDTO requestDTO) {
        SupplierResponseDTO response = supplierService.updateSupplier(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplierById(id);
        return ResponseEntity.noContent().build();
    }

}
