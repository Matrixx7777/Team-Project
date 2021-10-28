package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@CrossOrigin(origins = "*")
public class UserController {



    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserDto> getUser() {

        return new ArrayList<>();
    }

    @GetMapping(value = "getUser")
    public UserDto getUser(@RequestParam Long userId)  {
        return new UserDto(1L, "Test", "test");

    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) {

    }

    @PutMapping(value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return new UserDto(1L, "Test", "test");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return new UserDto(1L, "Test", "test");
    }
}