package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/v1/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService service;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService service, UserMapper userMapper) {
        this.service = service;
        this.userMapper = userMapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserDto> getUser() {
        List<User> users = service.getAllUsers();
        return userMapper.mapToUserDtoList(users);
    }

    @GetMapping(value = "getUser")
    public UserDto getUser(@RequestParam Long userId) {
        return userMapper.mapToUserDto(
                service.getUser(userId).orElseThrow(NullPointerException::new)
        );
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        try {
            service.deleteById(userId);
        } catch(NullPointerException e){
            System.out.println("Problem with delete user");
        }

    }

    @PutMapping(value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User updatedUser = service.updateUser(user);
        return userMapper.mapToUserDto(updatedUser);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        service.saveUser(user);
    }
}