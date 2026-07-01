package com.pinpon.inventory.management.product.controller;

import com.pinpon.inventory.management.product.dto.CreateProductRequestDTO;
import com.pinpon.inventory.management.product.dto.ResponseProductDTO;
import com.pinpon.inventory.management.product.dto.UpdateProductRequestDTO;
import com.pinpon.inventory.management.product.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Products API Operations")
public class ProductController {
    @Autowired
    private IProductService productService;


    @Operation(summary = "create a product", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PreAuthorize("hasAnyRole('ADMIN','MOD')")
    @PostMapping
    public ResponseEntity<ResponseProductDTO> createProduct(@Valid @RequestBody CreateProductRequestDTO requestDTO){
        ResponseProductDTO response = productService.createProduct(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping
    public ResponseEntity<List<ResponseProductDTO>> getAllProducts(){
        List<ResponseProductDTO> response = productService.findAllProducts();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> getProduct(@Parameter(description = "Product ID", example = "1")
                                                             @PathVariable Long id){
        ResponseProductDTO response = productService.findProductById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update product by id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PreAuthorize("hasAnyRole('ADMIN','MOD')")
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> updateProduct(@Parameter(description = "Product ID", example = "1")
                                                                @PathVariable Long id,
                                                                @Valid @RequestBody UpdateProductRequestDTO requestDTO){
        ResponseProductDTO response = productService.updateProduct(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete product by id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PreAuthorize("hasAnyRole('ADMIN','MOD')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> deleteProduct(@Parameter(description = "Product ID", example = "1")
                                                                @PathVariable Long id){
        productService.deleteProduct(id);
        return  ResponseEntity.noContent().build();
    }

}
