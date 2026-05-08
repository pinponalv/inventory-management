package com.pinpon.inventory.management.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdatedUserRequestDTO {

    private String name;
    private String lastName;
    @Email
    private String email;
    @Size(min = 6)
    private String password;
}
