package com.sahilsahudev.Blogging.services.impl;

import com.sahilsahudev.Blogging.exceptions.ResourceNotFoundException;
import com.sahilsahudev.Blogging.models.Comment;
import com.sahilsahudev.Blogging.models.Dto.CommentDto;
import com.sahilsahudev.Blogging.models.Post;
import com.sahilsahudev.Blogging.models.User;
import com.sahilsahudev.Blogging.payloads.comment.UpdateCommentRequest;
import com.sahilsahudev.Blogging.repositories.CommentRepository;
import com.sahilsahudev.Blogging.repositories.PostRepository;
import com.sahilsahudev.Blogging.repositories.UserRepository;
import com.sahilsahudev.Blogging.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto) {

        int userId = commentDto.getUserId();
        int postId = commentDto.getPostId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", Integer.toString(userId)));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", Integer.toString(postId)));

        Comment comment = Comment.builder()
                .details(commentDto.getDetails())
                .user(user)
                .post(post)
                .dateCreated(new Date())
                .build();

        Comment savedComment = commentRepository.save(comment);

        CommentDto savedCommentDto = modelMapper.map(savedComment, CommentDto.class);

        return savedCommentDto;
    }

    @Override
    public CommentDto updateComment(UpdateCommentRequest updateCommentRequest) {
        int id = updateCommentRequest.getCommentId();
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", Integer.toString(id)));

        comment.setDetails(updateCommentRequest.getDetails());

        Comment updatedComment = commentRepository.save(comment);

        return modelMapper.map(updatedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer comment_id) {
        Comment comment = commentRepository.findById(comment_id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", Integer.toString(comment_id)));

        commentRepository.deleteById(comment_id);
    }

    @Override
    public List<CommentDto> getCommentsForPost(Integer post_id) {

        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", Integer.toString(post_id)));

        List<Comment> comments = commentRepository.findAllByPostId(post_id);
        List<CommentDto> commentDtos = comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());

        return commentDtos;
    }

    @Override
    public CommentDto getComment(Integer commentId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", Integer.toString(commentId)));

        return modelMapper.map(comment, CommentDto.class);
    }
}
