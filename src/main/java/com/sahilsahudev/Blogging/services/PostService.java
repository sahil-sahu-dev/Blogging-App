package com.sahilsahudev.Blogging.services;

import com.sahilsahudev.Blogging.models.Dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostDto updatePost(PostDto postDto);
    PostDto getPost(Integer post_id);
    void deletePost(Integer postId);
    List<PostDto> getPostsByUser(Integer user_id);
}
