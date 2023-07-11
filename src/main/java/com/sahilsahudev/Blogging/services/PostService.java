package com.sahilsahudev.Blogging.services;

import com.sahilsahudev.Blogging.models.Dto.PostDto;
import com.sahilsahudev.Blogging.payloads.post.CreatePostRequest;
import com.sahilsahudev.Blogging.payloads.post.UpdatePostRequest;

import java.util.List;

public interface PostService {
    PostDto createPost(CreatePostRequest createPostRequest);
    PostDto updatePost(UpdatePostRequest updatePostRequest);
    PostDto getPost(Integer post_id);
    void deletePost(Integer postId);
    List<PostDto> getPostsByUser(Integer user_id);

    List<PostDto> fetchTimelineForUser(Integer user_id);

}
