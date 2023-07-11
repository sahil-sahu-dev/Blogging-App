package com.sahilsahudev.Blogging.controllers;

import com.sahilsahudev.Blogging.models.Dto.PostDto;
import com.sahilsahudev.Blogging.payloads.post.CreatePostRequest;
import com.sahilsahudev.Blogging.payloads.post.UpdatePostRequest;
import com.sahilsahudev.Blogging.services.PostService;
import com.sahilsahudev.Blogging.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer postId) {
        PostDto postDto = postService.getPost(postId);
        return ResponseEntity.ok(postDto);
    }

    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@RequestBody CreatePostRequest createPostRequest) {
        PostDto createdPostDto = postService.createPost(createPostRequest);
        return new ResponseEntity(createdPostDto, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<PostDto> updatePost(@RequestBody UpdatePostRequest updatePostRequest) {
        PostDto updatedPostDto = postService.updatePost(updatePostRequest);
        return new ResponseEntity(updatedPostDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDto> postDtos = postService.getPostsByUser(userId);
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/user/{userId}/timeline")
    public ResponseEntity<List<PostDto>> getTimelineForUser(@PathVariable Integer userId) {
        List<PostDto> postDtos = postService.fetchTimelineForUser(userId);
        return ResponseEntity.ok(postDtos);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<APIResponse> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(new APIResponse("Delete post successfully", true), HttpStatus.ACCEPTED);
    }

}
