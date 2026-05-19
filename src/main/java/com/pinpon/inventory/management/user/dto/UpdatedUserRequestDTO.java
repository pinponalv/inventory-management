package com.pinpon.inventory.management.user.dto;

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
@Setter
@Getter
public class UpdatedUserRequestDTO {

    @Schema(example = "David", description = "its the name of user")
    private String name;
    @Schema(example = "Trujeque", description = "its the last name of user")
    private String lastName;
    @Schema(example = "david@gmail.com", description = "email contact")
    @Email
    private String email;
    @Schema(example = "david123", description = "its the password")
    @Size(min = 6)
    private String password;
}
