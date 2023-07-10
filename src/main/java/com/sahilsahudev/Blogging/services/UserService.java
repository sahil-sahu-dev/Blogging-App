package com.sahilsahudev.Blogging.services;

import com.sahilsahudev.Blogging.models.Dto.UserDto;
import com.sahilsahudev.Blogging.models.User;

import java.util.List;

public interface UserService {
    UserDto updateUser(UserDto user, Integer user_id);
    UserDto getUserById(Integer user_id);
    List<UserDto> getAllUsers();
    void deleteUser(Integer user_id);
    void followUser(Integer user, Integer userToFollow);
    void unfollowUser(Integer user, Integer userToUnfollow);
    List<UserDto> getFollowers(Integer user_id);
    List<UserDto> getFollowing(Integer user_id);
}
