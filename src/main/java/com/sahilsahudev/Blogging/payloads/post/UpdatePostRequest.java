package com.sahilsahudev.Blogging.payloads.post;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostRequest {
    @NotNull(message = "post cannot exist without a user")
    private Integer postId;
    @NotEmpty
    private String title;
    @NotEmpty
    private String body;
}
