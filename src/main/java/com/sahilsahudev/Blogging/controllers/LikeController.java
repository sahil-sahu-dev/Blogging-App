package com.sahilsahudev.Blogging.controllers;

import com.sahilsahudev.Blogging.models.Dto.LikeDto;
import com.sahilsahudev.Blogging.services.LikeService;
import com.sahilsahudev.Blogging.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @GetMapping("/{likeId}")
    public ResponseEntity<LikeDto> getLike(@PathVariable Integer likeId) {

        LikeDto likeDto = likeService.getLike(likeId);
        return ResponseEntity.ok(likeDto);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<LikeDto>> getLikesForPost(@PathVariable Integer postId) {
        List<LikeDto> likeDtos = likeService.getLikesForPost(postId);
        return ResponseEntity.ok(likeDtos);
    }

    @PostMapping("/create")
    public ResponseEntity<LikeDto> createLike(@RequestBody LikeDto likeDto) {
        LikeDto createLikeDto = likeService.createLike(likeDto);
        return new ResponseEntity<>(createLikeDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<APIResponse> deleteLike(@PathVariable Integer likeId) {
        this.likeService.deleteLike(likeId);
        return new ResponseEntity<>(new APIResponse("Delete like successfully", true), HttpStatus.ACCEPTED);
    }

}
