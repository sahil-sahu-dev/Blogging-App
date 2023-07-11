package com.sahilsahudev.Blogging.payloads.post;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {
    @NotEmpty
    private String title;
    @NotEmpty
    private String body;
    @NotNull(message = "post cannot exist without a user")
    private Integer userId;
}
