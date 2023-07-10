package com.sahilsahudev.Blogging.controllers;

import com.sahilsahudev.Blogging.models.Dto.PostDto;
import com.sahilsahudev.Blogging.models.Like;
import com.sahilsahudev.Blogging.models.Post;
import com.sahilsahudev.Blogging.services.PostService;
import com.sahilsahudev.Blogging.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
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
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto createdPostDto = postService.createPost(postDto);
        return new ResponseEntity(createdPostDto, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto) {
        PostDto updatedPostDto = postService.updatePost(postDto);
        return new ResponseEntity(updatedPostDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDto> postDtos = postService.getPostsByUser(userId);
        return ResponseEntity.ok(postDtos);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<APIResponse> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(new APIResponse("Delete post successfully", true), HttpStatus.ACCEPTED);
    }

}
