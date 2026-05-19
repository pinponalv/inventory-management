package com.pinpon.inventory.management.supplier.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateSupplierRequestDTO {
    @Schema(example = "David Trujeque", description = "its the name of supplier ")
    @NotBlank
    private String name;

    @Schema(example = "This supplier delivers cell phones to me", description = "Description of the products you offer me")
    @NotBlank
    private String description;

    @Schema(example = "david@gmail.com", description = "email contact")
    @Email
    @NotBlank
    private String email;

    @Schema(example = "6652341204", description = "phone number")
    @NotBlank
    @Size(min = 10, max = 10)
    private String phone;
}
