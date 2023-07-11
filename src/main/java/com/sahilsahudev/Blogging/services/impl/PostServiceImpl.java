package com.sahilsahudev.Blogging.services.impl;

import com.sahilsahudev.Blogging.exceptions.ResourceNotFoundException;
import com.sahilsahudev.Blogging.models.Dto.PostDto;
import com.sahilsahudev.Blogging.models.Dto.UserDto;
import com.sahilsahudev.Blogging.models.Post;
import com.sahilsahudev.Blogging.models.User;
import com.sahilsahudev.Blogging.payloads.post.CreatePostRequest;
import com.sahilsahudev.Blogging.payloads.post.UpdatePostRequest;
import com.sahilsahudev.Blogging.repositories.PostRepository;
import com.sahilsahudev.Blogging.repositories.UserRepository;
import com.sahilsahudev.Blogging.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(CreatePostRequest createPostRequest) {
        int userId = createPostRequest.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", Integer.toString(userId)));

        PostDto postDto = PostDto.builder()
                .title(createPostRequest.getTitle())
                .body(createPostRequest.getBody())
                .dateCreated(new Date())
                .user(userToDto(user))
                .build();

        Post post = dtoToPost(postDto);
        Post savedPost = postRepository.save(post);
        return postToDto(savedPost);
    }

    @Override
    public PostDto updatePost(UpdatePostRequest updatePostRequest) {
        int id = updatePostRequest.getPostId();
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", Integer.toString(id)));

        post.setBody(updatePostRequest.getBody());
        post.setTitle(updatePostRequest.getTitle());

        return postToDto(post);
    }

    @Override
    public PostDto getPost(Integer post_id) {

        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", Integer.toString(post_id)));

        return postToDto(post);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", Integer.toString(postId)));

        postRepository.deleteById(postId);
    }

    @Override
    public List<PostDto> getPostsByUser(Integer user_id) {
        List<Post> posts = this.postRepository.findByUserId(user_id);
        List<PostDto> postDtos = posts.stream().map(post -> this.postToDto(post)).collect(Collectors.toList());
        return postDtos;

    }

    private Post dtoToPost(PostDto postDto) {
        Post post = this.modelMapper.map(postDto, Post.class);
        return post;
    }

    private PostDto postToDto(Post post) {

        PostDto postDto = this.modelMapper.map(post, PostDto.class);
        return postDto;
    }

    private User dtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    private UserDto userToDto(User user) {

        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
