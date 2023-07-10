package com.sahilsahudev.Blogging.controllers;


import com.sahilsahudev.Blogging.models.Dto.UserDto;
import com.sahilsahudev.Blogging.services.UserService;
import com.sahilsahudev.Blogging.utils.APIResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    //GET: get user
    @GetMapping("/{user_id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer user_id) {
        UserDto userDto = userService.getUserById(user_id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    //PUT: update user
    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto) {
        UserDto updated_user = this.userService.updateUser(userDto, userDto.getId());
        return ResponseEntity.ok(updated_user);
    }
    //DELETE: delete user
    @DeleteMapping("/{user_id}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable Integer user_id) {
        this.userService.deleteUser(user_id);
        return ResponseEntity.ok(new APIResponse("Deleted User Successfully", true));
    }
}