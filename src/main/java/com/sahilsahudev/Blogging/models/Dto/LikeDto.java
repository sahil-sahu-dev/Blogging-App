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
@NoArgsConstructor
@AllArgsConstructor
public class LikeDto {
    @NotNull(message = "id cannot be null for like dto")
    private int id;
    @NotNull(message = "like must be associated with a user")
    private User user;
    @NotNull(message = "like must be associated with a post")
    private Post post;
}
