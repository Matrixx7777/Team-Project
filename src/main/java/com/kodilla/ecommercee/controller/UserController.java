package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDbService userDbService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") long userId) {
        User user = userDbService.getUser(userId);
        return userMapper.mapToUserDto(user);
    }

    @GetMapping
    public List<UserDto> getUsers()  {
        List<User> users = userDbService.getUsers();
        return userMapper.mapToUserDtoList(users);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") long userId) {
        userDbService.deleteUser(userId);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User updatedUser = userDbService.updateUser(user);
        return userMapper.mapToUserDto(updatedUser);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User savedUser = userDbService.saveUser(user);
        return userMapper.mapToUserDto(savedUser);
    }
}