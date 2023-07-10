package com.sahilsahudev.Blogging.models.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    @NotNull
    private int id;
    @NotNull(message = "Name cannot be empty")
    private String name;
    @Email(message = "Email cannot be empty")
    private String email;
}
