package com.sahilsahudev.Blogging.services;

import com.sahilsahudev.Blogging.models.Dto.LikeDto;

import java.util.List;

public interface LikeService {
    LikeDto createLike(LikeDto likeDto);
    LikeDto getLike(Integer likeId);
    void deleteLike(Integer like_id);
    List<LikeDto> getLikesForPost(Integer post_id);
}
