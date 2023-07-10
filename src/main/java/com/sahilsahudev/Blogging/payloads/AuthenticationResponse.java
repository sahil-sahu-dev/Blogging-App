package com.sahilsahudev.Blogging.payloads;

import com.sahilsahudev.Blogging.models.Dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private UserDto user;
    private String token;
}
