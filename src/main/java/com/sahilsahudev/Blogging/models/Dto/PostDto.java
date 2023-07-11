package com.sahilsahudev.Blogging.models.Dto;

import com.sahilsahudev.Blogging.models.Like;
import com.sahilsahudev.Blogging.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    @NotNull(message = "post cannot exist without an id")
    private int id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String body;
    @Temporal(value = TemporalType.TIMESTAMP)
    @NotNull(message = "dateCreated cannot be null")
    private Date dateCreated;
    @NotNull(message = "post cannot exist without a user")
    private UserDto user;

    private List<LikeDto> likes;
    private List<CommentDto> comments;
}
