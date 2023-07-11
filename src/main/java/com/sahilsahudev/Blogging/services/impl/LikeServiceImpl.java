package com.sahilsahudev.Blogging.services.impl;

import com.sahilsahudev.Blogging.exceptions.ResourceNotFoundException;
import com.sahilsahudev.Blogging.models.Dto.CommentDto;
import com.sahilsahudev.Blogging.models.Dto.LikeDto;
import com.sahilsahudev.Blogging.models.Like;
import com.sahilsahudev.Blogging.models.Post;
import com.sahilsahudev.Blogging.models.User;
import com.sahilsahudev.Blogging.repositories.LikeRepository;
import com.sahilsahudev.Blogging.repositories.PostRepository;
import com.sahilsahudev.Blogging.repositories.UserRepository;
import com.sahilsahudev.Blogging.services.LikeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LikeDto createLike(LikeDto likeDto) {

        int userId = likeDto.getUserId();
        int postId = likeDto.getPostId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", Integer.toString(userId)));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", Integer.toString(postId)));

        Like like = Like.builder()
                .post(post)
                .user(user)
                .build();

        Like createLike = likeRepository.save(like);

        return modelMapper.map(createLike, LikeDto.class);
    }

    @Override
    public LikeDto getLike(Integer likeId) {
        Like like = likeRepository.findById(likeId)
                .orElseThrow(() -> new ResourceNotFoundException("Like", "Id", Integer.toString(likeId)));

        return modelMapper.map(like, LikeDto.class);
    }


    @Override
    public void deleteLike(Integer like_id) {
        Like like = likeRepository.findById(like_id)
                .orElseThrow(() -> new ResourceNotFoundException("Like", "Id", Integer.toString(like_id)));
        likeRepository.deleteById(like_id);
    }

    @Override
    public List<LikeDto> getLikesForPost(Integer post_id) {

        List<Like> likes = likeRepository.findAllByPostId(post_id);

        List<LikeDto> likeDtos = likes.stream().map(like -> modelMapper.map(like, LikeDto.class)).collect(Collectors.toList());

        return likeDtos;
    }
}
