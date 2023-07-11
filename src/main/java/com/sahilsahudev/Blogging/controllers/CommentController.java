package com.sahilsahudev.Blogging.controllers;

import com.sahilsahudev.Blogging.models.Dto.CommentDto;
import com.sahilsahudev.Blogging.payloads.comment.UpdateCommentRequest;
import com.sahilsahudev.Blogging.services.CommentService;
import com.sahilsahudev.Blogging.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {

        CommentDto createdComment = commentService.createComment(commentDto);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Integer commentId) {
        CommentDto commentDto = commentService.getComment(commentId);
        return ResponseEntity.ok(commentDto);
    }

    @PutMapping("/update")
    public ResponseEntity<CommentDto> updateComment(@RequestBody UpdateCommentRequest updateCommentRequest) {
        CommentDto updatedComment = commentService.updateComment(updateCommentRequest);
        return new ResponseEntity<>(updatedComment, HttpStatus.ACCEPTED);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPost(@PathVariable Integer postId) {
        List<CommentDto> commentDtos = commentService.getCommentsForPost(postId);
        return new ResponseEntity<>(commentDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<APIResponse> deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new APIResponse("delete comment successfully", true), HttpStatus.ACCEPTED);
    }
}
