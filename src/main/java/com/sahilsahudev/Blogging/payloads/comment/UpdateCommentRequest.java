package com.sahilsahudev.Blogging.payloads.comment;

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
public class UpdateCommentRequest {
    @NotNull
    private Integer commentId;
    @NotEmpty
    private String details;
}
