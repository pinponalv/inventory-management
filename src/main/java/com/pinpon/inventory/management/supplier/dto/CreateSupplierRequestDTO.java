package com.pinpon.inventory.management.supplier.dto;

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

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 10)
    private String phone;
}
