package com.sahilsahudev.Blogging.services.impl;

import com.sahilsahudev.Blogging.exceptions.ResourceNotFoundException;
import com.sahilsahudev.Blogging.models.Dto.UserDto;
import com.sahilsahudev.Blogging.models.User;
import com.sahilsahudev.Blogging.repositories.UserRepository;
import com.sahilsahudev.Blogging.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto updateUser(UserDto userDto, Integer user_id) {
        User user = this.userRepository.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", user_id.toString()));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        User updated_user = this.userRepository.save(user);

        return this.userToDto(updated_user);
    }

    @Override
    public UserDto getUserById(Integer user_id) {
        User user = this.userRepository.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", user_id.toString()));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> users_dto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return users_dto;
    }

    @Override
    public void deleteUser(Integer user_id) {
        User user = this.userRepository.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", user_id.toString()));

        this.userRepository.delete(user);

    }

    @Override
    public void followUser(Integer user, Integer userToFollow) {

    }

    @Override
    public void unfollowUser(Integer user, Integer userToUnfollow) {

    }

    @Override
    public List<UserDto> getFollowers(Integer user_id) {
        return null;
    }

    @Override
    public List<UserDto> getFollowing(Integer user_id) {
        return null;
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