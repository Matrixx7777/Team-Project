package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.service.CartDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapper {


    private final CartDbService  cartDbService;


    public User mapToUser(final UserDto userDto){

        List<Cart> carts = cartDbService.getCarts();

        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                carts
        );
    }

    public UserDto mapToUserDto(final User user){


        final List<CartDto> cartDtoList = user.getCartList().stream().map(CartMapper::mapToCartDto).collect(Collectors.toList());
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                cartDtoList
        );
    }


    public List<UserDto> mapToUserDtoList(final List<User> userList){
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

}


