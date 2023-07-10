package com.sahilsahudev.Blogging.models.Dto;

import com.sahilsahudev.Blogging.models.Post;
import com.sahilsahudev.Blogging.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    @NotNull(message = "post id cannot be null")
    private int id;
    @NotNull(message = "comment must be associated with a post")
    private Post post;
    @NotNull(message = "comment must be associated with a user")
    private User user;
    String details;
}
