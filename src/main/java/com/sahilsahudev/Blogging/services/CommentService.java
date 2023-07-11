package com.sahilsahudev.Blogging.services;

import com.sahilsahudev.Blogging.models.Dto.CommentDto;
import com.sahilsahudev.Blogging.payloads.comment.UpdateCommentRequest;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto);
    CommentDto getComment(Integer commentId);
    CommentDto updateComment(UpdateCommentRequest updateCommentRequest);
    void deleteComment(Integer comment_id);
    List<CommentDto> getCommentsForPost(Integer post_id);

}
