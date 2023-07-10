package com.sahilsahudev.Blogging.services;

import com.sahilsahudev.Blogging.models.Dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto);
    CommentDto updateComment(CommentDto commentDto);
    CommentDto deleteComment(Integer comment_id);
    List<CommentDto> getCommentsForPost(Integer post_id);

}
