package com.pinpon.inventory.management.supplier.controller;

import com.pinpon.inventory.management.supplier.dto.CreateSupplierRequestDTO;
import com.pinpon.inventory.management.supplier.dto.SupplierResponseDTO;
import com.pinpon.inventory.management.supplier.dto.UpdateSupplierRequestDTO;
import com.pinpon.inventory.management.supplier.service.ISupplierService;
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
@RequestMapping("/api/suppliers")
@Tag(name = "Supplier", description = "Supplier API Operations")
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;


    @Operation(summary = "create a supplier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping
    public ResponseEntity<SupplierResponseDTO> createSupplier(@RequestBody CreateSupplierRequestDTO requestDTO) {
        SupplierResponseDTO supplierResponseDTO = supplierService.createSupplier(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierResponseDTO);
    }

    @Operation(summary = "Get all suppliers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers() {
        List<SupplierResponseDTO> response = supplierService.findAllSuppliers();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get supplier by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getSupplier(@Parameter(description = "Supplier ID", example = "1")
                                                               @PathVariable Long id) {
        SupplierResponseDTO response = supplierService.findSupplierById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get supplier by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<SupplierResponseDTO> getSupplierByEmail(@Parameter(description = "Supplier email", example = "supplier@email.com")
                                                                      @PathVariable String email) {
        SupplierResponseDTO response = supplierService.findSupplierByEmail(email);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update supplier by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> updateSupplier(@Parameter(description = "Supplier ID", example = "1")
                                                                  @PathVariable Long id,
                                                              @RequestBody UpdateSupplierRequestDTO requestDTO) {
        SupplierResponseDTO response = supplierService.updateSupplier(id, requestDTO);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Delete supplier by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Supplier deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> deleteSupplier(@Parameter(description = "Supplier ID", example = "1")
                                                                  @PathVariable Long id) {
        supplierService.deleteSupplierById(id);
        return ResponseEntity.noContent().build();
    }

}
