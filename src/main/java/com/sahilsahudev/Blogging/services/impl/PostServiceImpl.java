package com.sahilsahudev.Blogging.services.impl;

import com.sahilsahudev.Blogging.exceptions.ResourceNotFoundException;
import com.sahilsahudev.Blogging.models.Dto.PostDto;
import com.sahilsahudev.Blogging.models.Post;
import com.sahilsahudev.Blogging.repositories.PostRepository;
import com.sahilsahudev.Blogging.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = dtoToPost(postDto);
        Post savedPost = postRepository.save(post);
        return postToDto(savedPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        int id = postDto.getId();
        Post post = postRepository.findById(postDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", Integer.toString(id)));

        post.setBody(postDto.getBody());
        post.setTitle(postDto.getTitle());

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
        List<Post> posts = this.postRepository.findByUser(user_id);
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
}
